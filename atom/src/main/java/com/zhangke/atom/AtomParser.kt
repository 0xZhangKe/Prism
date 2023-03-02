package com.zhangke.atom

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zhangke.atom.elements.AtomElement
import com.zhangke.atom.elements.AtomFeed
import org.json.XML

class AtomParser {

    private val gson: Gson get() = AtomParserConfig.gson

    fun parse(xml: String): AtomElement {
        val jsonObject = xmlToJsonObject(xml)
        val feedJson = jsonObject.get("feed").asJsonObject
        return gson.fromJson(feedJson, AtomFeed::class.java)
    }

    private fun xmlToJsonObject(xml: String): JsonObject {
        val jsonString = XML.toJSONObject(xml).toString()
        return gson.fromJson(jsonString, JsonObject::class.java)
    }
}