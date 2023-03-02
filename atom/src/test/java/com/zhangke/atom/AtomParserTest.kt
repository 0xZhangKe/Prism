package com.zhangke.atom

import com.rometools.rome.io.SyndFeedInput
import com.zhangke.atom.util.gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.io.File

class AtomParserTest {

    private val atomParser = AtomParser()

    @Test
    fun testDeserialization() {
        val parser = AtomParser()
        collectAllDocuments().forEach { doc ->
            val feed = parser.parse(doc)
            try {
                println(gson.toJsonTree(feed).toString())
            } catch (e: Throwable) {
                println(feed)
                throw e
            }
        }
    }

    @Test
    fun printAllElement() {
        val parser = AtomParser()
        collectAllDocuments().forEach { doc ->
            println(parser.parse(doc))
        }
    }

    @Test
    fun printSpecifiedDoc() {
        println(atomParser.parse(File("TestFile.xml").readText()))
    }

    private fun collectAllDocuments(): List<String> {
        val repoDir = DocRepoHelper().getAtomRepoDir()
        return repoDir.listFiles()?.map { it.readText() } ?: emptyList()
    }

    @Test
    fun parse() {
        runBlocking {
            getAtomDocFlow().collect { docFile ->
//                println(rssParser.parse(doc))
                try {
                    val feed = SyndFeedInput().build(docFile)
                    println(feed)
                } catch (e: Exception) {
                    println("---------START---------")
                    println("---------START---------")
                    println("---------START---------")
                    println(docFile.absolutePath)
                    println(docFile.readText())
                    println("---------END---------")
                    println("---------END---------")
                    println("---------END---------")
                }
            }
        }
    }

    private fun getAtomDocFlow(): Flow<File> {
        val dir = DocRepoHelper().getAtomRepoDir()
        return flow {
            dir.listFiles()?.forEach { docFile ->
                emit(docFile)
            }
        }
    }
}