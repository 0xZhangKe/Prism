package com.zhangke.rss

import com.rometools.rome.io.SyndFeedInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.io.File

class RssParserTest {

    @Test
    fun parse() {
        val rssParser = RssParser()
        runBlocking {
            getRssDocFlow().collect { docFile ->
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

    private fun getRssDocFlow(): Flow<File> {
        val dir = DocRepoHelper().getRssRepoDir()
        return flow {
            dir.listFiles()?.forEach { docFile ->
                emit(docFile)
            }
        }
    }
}