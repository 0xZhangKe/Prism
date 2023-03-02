package com.zhangke.prism.sample

import com.google.gson.JsonObject
import java.io.File

class SourceHelper {

    fun readAllAtomSource(): List<String> {
        return readSpecifiedArray("atom")
    }

    fun readAllRssSource(): List<String> {
        return readSpecifiedArray("rss")
    }

    private fun readSpecifiedArray(name: String): List<String> {
        val file = File(URL_FILE_PATH)
        return gson.fromJson(file.readText(), JsonObject::class.java)
            .getAsJsonArray(name)
            .map { it.asString }
    }
}