package com.zhangke.atom

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.util.gson
import org.junit.jupiter.api.Test

class JsonAdapterTest {

    @Test
    fun testObjectLink() {
        val jsonString = """
            {
              "name":"zhangke",
              "link": {
                "baseUrl": "www.zhangke.com",
                "url": "/blog"
              }
            }
        """.trimIndent()
        testLink(jsonString, "OBJECT")
    }

    @Test
    fun testStringLink() {
        val jsonString = """
            {
              "name":"zhangke",
              "link": "www.zhangke.com"
            }
        """.trimIndent()
        testLink(jsonString, "STRING")
    }

    @Test
    fun testEmptyLink() {
        val jsonString = """
            {
              "name":"zhangke",
              "link": ""
            }
        """.trimIndent()
        testLink(jsonString, "EMPTY")
    }

    @Test
    fun testNullLink() {
        val jsonString = """
            {
              "name":"zhangke"
            }
        """.trimIndent()
        testLink(jsonString, "NULL")
    }

    @Test
    fun testRichLink() {
        val jsonString = """
            {
              "name":"zhangke",
              "link": {
                "baseUrl": "www.zhangke.com",
                "url": "/blog",
                "path": "/some"
              }
            }
        """.trimIndent()
        testLink(jsonString, "Rich")
    }

    @Test
    fun testHomePage(){
        val jsonString = """
            {
              "name": "zhangke",
              "homepage": {
                "title": "Title",
                "link": {
                  "baseUrl": "www.zhangke.com",
                  "url": "/blog"
                }
              }
            }
        """.trimIndent()
        testLink(jsonString, "HomePage")
    }

    private fun testLink(jsonString: String, tag: String) {
        val user = gson.fromJson(jsonString, User::class.java)
        println()
        println("---------$tag------------")
        println(user)
        println(gson.toJson(user))
        println("---------$tag------------")
    }

    data class User(
        val name: String,
        val homepage: Homepage?,
    )

    @JsonAdapter(HomePageAdapter::class)
    data class Homepage(
        val title: String,
        val link: Link?
    )

    class HomePageAdapter : TypeAdapter<Homepage>() {

        override fun write(out: JsonWriter, value: Homepage) {
            out.beginObject()
            out.name("title").value(value.title)
            value.link?.let {
                out.name("link")
                LinkAdapter().write(out, it)
            }
            out.endObject()
        }

        override fun read(input: JsonReader): Homepage {
            var title: String? = null
            var link: Link? = null
            input.beginObject()
            while (input.hasNext()) {
                when (input.nextName()) {
                    "title" -> title = input.nextString()
                    "link" -> link = LinkAdapter().read(input)
                    else -> input.skipValue()
                }
            }
            input.endObject()
            return Homepage(title!!, link)
        }
    }

    @JsonAdapter(LinkAdapter::class)
    data class Link(
        val baseUrl: String?,
        val url: String
    )

    class LinkAdapter : TypeAdapter<Link>() {

        override fun write(out: JsonWriter, value: Link) {
            if (value.baseUrl.isNullOrEmpty()) {
                out.value(value.url)
            } else {
                out.beginObject()
                out.name("baseUrl").value(value.baseUrl)
                out.name("url").value(value.url)
                out.endObject()
            }
        }

        override fun read(input: JsonReader): Link {
            var baseUrl: String? = null
            var url: String? = null
            val typeToken = input.peek()
            if (typeToken == JsonToken.BEGIN_OBJECT) {
                input.beginObject()
                while (input.hasNext()) {
                    when (input.nextName()) {
                        "baseUrl" -> baseUrl = input.nextString()
                        "url" -> url = input.nextString()
                        else -> input.skipValue()
                    }
                }
                input.endObject()
            } else {
                url = input.nextString()
            }
            return Link(baseUrl, url!!)
        }
    }
}