package com.zhangke.rss

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zhangke.rss.rss.elements.Rss
import org.json.XML

class RssParser {

    private val gson: Gson get() = RssParserConfig.gson

    fun parse(xml: String): Rss {
        val xmlToJson = xmlToJsonObject(xml)
        val rssJson = xmlToJson.getAsJsonObject("rss")
        return try {
            gson.fromJson(rssJson, Rss::class.java)
        } catch (e: Throwable) {
            println(rssJson)
            throw e
        }
    }

    private fun xmlToJsonObject(xml: String): JsonObject {
        val jsonString = XML.toJSONObject(xml).toString()
        return gson.fromJson(jsonString, JsonObject::class.java)
    }
}