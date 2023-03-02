package com.zhangke.prism.sample

import com.zhangke.prism.sample.utils.HttpUtil
import com.zhangke.prism.sample.utils.Md5
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.io.File

fun main() {
//    downloadAtoms()
    downloadRss()
}

/**
 * Download Atom docs.
 */
private fun downloadAtoms() {
    val atomRepoDir = getAtomRepoDir()
    if (!atomRepoDir.exists()) {
        atomRepoDir.mkdirs()
    }
    val atomUrlList = SourceHelper().readAllAtomSource()
    runBlocking {
        atomUrlList.map { url ->
            async { downloadFile(url, atomRepoDir) }
        }.awaitAll()
    }
}

private fun downloadRss(){
    val rssRepoDir = getRssRepoDir()
    if (!rssRepoDir.exists()){
        rssRepoDir.mkdirs()
    }
    val rssUrlList = SourceHelper().readAllRssSource()
    runBlocking {
        rssUrlList.map { url ->
            async { downloadFile(url, rssRepoDir) }
        }.awaitAll()
    }
}

private fun downloadFile(url: String, dir: File) {
    val fileName = Md5.md5(url)
    val destFile = File(dir, fileName)
    if (destFile.exists()) return
    runCatching { HttpUtil.get(url) }
        .onFailure {
            System.err.println(it)
        }
        .onSuccess {
            if (it.isNullOrEmpty()) return@onSuccess
            println(destFile.absolutePath)
            destFile.createNewFile()
            destFile.writeText(it)
        }
}

private fun getDocRepoDir(): File {
    return File("DocRepo/")
}

fun getAtomRepoDir(): File {
    return File(getDocRepoDir(), "Atom/")
}

fun getRssRepoDir(): File {
    return File(getDocRepoDir(), "Rss/")
}