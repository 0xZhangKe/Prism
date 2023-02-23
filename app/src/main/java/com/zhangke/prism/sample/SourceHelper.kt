package com.zhangke.prism.sample

import com.google.gson.JsonObject
import java.io.File

class SourceHelper {

    fun readAllAtomSource(): List<String> {
        val file = File(URL_FILE_PATH)
        return gson.fromJson(file.readText(), JsonObject::class.java)
            .getAsJsonArray("atom")
            .map { it.asString }
    }
}