package com.zhangke.atom

import com.zhangke.atom.util.gson
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
}