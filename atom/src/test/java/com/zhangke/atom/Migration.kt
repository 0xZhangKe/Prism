package com.zhangke.atom

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.zhangke.atom.util.HttpUtil
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Node
import org.xml.sax.InputSource
import java.io.File
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

private const val SRC_FILE_PATH = "/Users/zhangke/Downloads/InoreaderFeeds.xml"
internal const val URL_FILE_PATH = "UrlList.json"

fun main() {
    val sourceFile = File(SRC_FILE_PATH)
    val doc = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder()
        .parse(InputSource(StringReader(sourceFile.readText())))
    val list = mutableListOf<String>()
    collectOutline(doc.childNodes.item(0), list)

    val atomJsonArray = JsonArray()
    val rssJsonArray = JsonArray()
    val json = JsonObject()
    json.add("atom", atomJsonArray)
    json.add("rss", rssJsonArray)

    runBlocking {
        list.map {
            async {
                try {
                    if (isAtomDoc(it)) {
                        atomJsonArray.add(it)
                    } else {
                        rssJsonArray.add(it)
                    }
                } catch (e: Throwable) {
                    // ignore
                }
            }
        }.awaitAll()
    }

    val destFile = File(URL_FILE_PATH)
    if (!destFile.exists()) {
        destFile.createNewFile()
    }
    destFile.writeText(json.toString())
    println(destFile.absolutePath)
}

private fun isAtomDoc(url: String): Boolean {
    val response = HttpUtil.get(url)!!
    val doc = try {
        DocumentBuilderFactory.newInstance()
            .newDocumentBuilder()
            .parse(InputSource(StringReader(response)))
    } catch (e: Throwable) {
        println("Error: $url is not a validate xml. Cause: ${e.message}")
        throw e
    }
    var isAtom = false
    val children = doc.childNodes
    for (i in 0 until doc.childNodes.length) {
        if (children.item(i).nodeName == "feed") {
            isAtom = true
            break
        }
    }
    println("${if (isAtom) "Atom:" else "RSS"}: $url")
    return isAtom
}

private fun collectOutline(node: Node, list: MutableList<String>) {
    if (node.nodeName == "outline") {
        val attributes = node.attributes
        for (i in 0 until attributes.length) {
            val item = attributes.item(i)
            if (item.nodeName == "xmlUrl") {
                list.add(item.nodeValue)
            }
        }
    }
    val children = node.childNodes
    for (i in 0 until children.length) {
        collectOutline(children.item(i), list)
    }
}