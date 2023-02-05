package com.zhangke.atom.metadata

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.junit.jupiter.api.Test

class AtomContentTest {

    private val gson = Gson()

    @Test
    fun testTextContent() {
        val text = "this is text content"
        buildFeedJson {
            addProperty("type", "text")
            addProperty("text", text)
        }.let {
            val feed = gson.fromJson(it, Feed::class.java).content
            assert(feed is AtomContent.InlineTextContent)
            feed as AtomContent.InlineTextContent
            assert(feed.commonAttributes == null)
            assert(feed.text == text)
        }
    }

    @Test
    fun testHtmlContent() {
        val text = "<p>this is html content</p>"
        buildFeedJson {
            addProperty("type", "html")
            addProperty("text", text)
        }.let {
            val feed = gson.fromJson(it, Feed::class.java).content
            assert(feed is AtomContent.InlineHtmlContent)
            feed as AtomContent.InlineHtmlContent
            assert(feed.html == text)
        }
    }

    @Test
    fun testXHtmlContent() {
        val text = "<p>this is html content</p>"
        buildFeedJson {
            addProperty("type", "xhtml")
            addProperty("xhtmlDiv", text)
        }.let {
            val feed = gson.fromJson(it, Feed::class.java).content
            assert(feed is AtomContent.InlineXHTMLContent)
            assert((feed as AtomContent.InlineXHTMLContent).xhtmlDiv == text)
        }
    }

    @Test
    fun testMediaContent() {
        val source = "https://zhangke.com/video.mp4"
        buildFeedJson {
            addProperty("type", "video")
            addProperty("src", source)
        }.let {
            val feed = gson.fromJson(it, Feed::class.java).content
            assert(feed is AtomContent.MediaContent)
            feed as AtomContent.MediaContent
            assert(feed.src == source)
        }
    }

    @Test
    fun testOtherContent() {
        val text = "this is xml doc."
        buildFeedJson {
            addProperty("type", "xml")
            addProperty("text", text)
        }.let {
            val feed = gson.fromJson(it, Feed::class.java).content
            assert(feed is AtomContent.InlineOtherContent)
            feed as AtomContent.InlineOtherContent
            assert(feed.text == text)
        }
    }

    private fun buildFeedJson(content: JsonObject.() -> Unit): JsonObject {
        return JsonObject().apply {
            add("content", JsonObject().apply(content))
        }
    }
}

data class Feed(
    val content: AtomContent
)