package com.zhangke.atom

import com.google.gson.JsonObject
import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.util.HttpUtil
import com.zhangke.atom.util.gson
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.json.XML
import org.junit.jupiter.api.Test
import java.io.File

class AtomParserTest {

    private val atomParser = AtomParser()

    private val testFile = File("TestFile.xml")

    @Test
    fun testJson() {
        val json = JsonObject().apply {
            addProperty("base", "www.google.com")
            addProperty("lang", "zh-cn")
            addProperty("url", "/search?query=zhang")
        }
        val jsonObject = gson.fromJson(json, Foo::class.java)
        println(jsonObject)
    }

    data class Foo(
        override val base: String?,
        override val lang: String?,
        val url: String
    ) : AtomCommonAttributes

    @Test
    fun printAllElement() {
//        val xmlDocList = collectXmlDocuments()
        runBlocking {
            fetchAtomDoc().collect {
                val element = atomParser.parse(it)
                println(element)
            }
        }
    }

    @Test
    fun printSpecifiedDoc() {
        println(atomParser.parse(testFile.readText()))
    }

    private fun fetchAtomDoc(): Flow<String> {
        val urlList = SourceHelper().readAllAtomSource().take(10)
        return flow {
            urlList.forEach {
                val result = HttpUtil.get(it)
                if (!result.isNullOrEmpty()) {
                    emit(result)
                }
            }
        }
    }

    private fun collectXmlDocuments(): List<String> {
        val urlList = SourceHelper().readAllAtomSource().take(1)
        return runBlocking {
            urlList.map {
                async { HttpUtil.get(it) }
            }.awaitAll()
        }.filter { !it.isNullOrEmpty() }.map { it!! }
    }
}