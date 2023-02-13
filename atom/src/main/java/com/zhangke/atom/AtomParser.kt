package com.zhangke.atom

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zhangke.atom.elements.AtomElement
import com.zhangke.atom.elements.AtomFeed
import org.json.XML

class AtomParser {

    private val gson: Gson get() = AtomConfig.gson

    fun parse(xml: String): AtomElement? {
        val jsonObject = xmlToJsonObject(xml) ?: return null
        val feedJson = jsonObject.get("feed")
            ?.takeIf { it.isJsonObject }
            ?.asJsonObject
            ?: return null
        return gson.fromJson(feedJson, AtomFeed::class.java)
    }

    private fun xmlToJsonObject(xml: String): JsonObject? {
        return try {
            val jsonString = XML.toJSONObject(xml).toString()
            gson.fromJson(jsonString, JsonObject::class.java)
        } catch (e: Exception) {
            null
        }
    }
}