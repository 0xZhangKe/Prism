package com.zhangke.atom

import com.google.gson.JsonObject
import com.zhangke.atom.util.gson
import java.io.File

class SourceHelper {

    fun readAllAtomSource(): List<String> {
        val file = File("../${URL_FILE_PATH}")
        return gson.fromJson(file.readText(), JsonObject::class.java)
            .getAsJsonArray("atom")
            .map { it.asString }
    }
}