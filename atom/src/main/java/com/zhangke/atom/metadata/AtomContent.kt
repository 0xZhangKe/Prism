package com.zhangke.atom.metadata

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.adapters.AtomCommonAttributesAdapter
import com.zhangke.atom.constructs.AtomText

/**
 * The "atom:content" element either contains or links to the content of the entry.
 * The content of atom:content is Language-Sensitive.
 *
 * On the atom:content element, the value of the "type" attribute MAY be
 * one of "text", "html", or "xhtml".  Failing that, it MUST conform to
 * the syntax of a MIME media type, but MUST NOT be a composite type
 * (see Section 4.2.6 of [MIMEREG]).  If neither the type attribute nor
 * the src attribute is provided, Atom Processors MUST behave as though
 * the type attribute were present with a value of "text".
 */
@JsonAdapter(AtomContentJsonAdapter::class)
sealed class AtomContent {

    data class InlineTextContent(
        val commonAttributes: AtomCommonAttributes?,
        val text: String,
    ) : AtomContent()

    data class InlineHtmlContent(
        val commonAttributes: AtomCommonAttributes?,
        val html: String,
    ) : AtomContent()

    data class InlineXHTMLContent(
        val commonAttributes: AtomCommonAttributes?,
        val xhtmlDiv: String
    ) : AtomContent()

    data class InlineOtherContent(
        val commonAttributes: AtomCommonAttributes?,
        val text: String,
        val mimeType: String?,
    ) : AtomContent()

    data class MediaContent(
        val commonAttributes: AtomCommonAttributes?,
        val src: String,
        val mimeType: String?,
    ) : AtomContent()

    companion object {

        fun create(
            commonAttributes: AtomCommonAttributes?,
            type: String?,
            src: String?,
            text: String?,
            xhtml: String?
        ): AtomContent {
            return when {
                type == AtomText.TYPE_TEXT || (type == null && src == null) -> {
                    // is text type
                    InlineTextContent(commonAttributes, text.orEmpty())
                }

                type == AtomText.TYPE_HTML -> InlineHtmlContent(commonAttributes, text.orEmpty())
                type == AtomText.TYPE_XHTML -> InlineXHTMLContent(commonAttributes, xhtml.orEmpty())
                src != null -> MediaContent(commonAttributes, src, type)
                else -> InlineOtherContent(commonAttributes, text.orEmpty(), type)
            }
        }
    }
}

internal class AtomContentJsonAdapter : TypeAdapter<AtomContent>() {

    private val commonAttributesAdapter = AtomCommonAttributesAdapter()

    override fun write(out: JsonWriter, value: AtomContent) {
        out.beginObject()

        when (value) {
            is AtomContent.InlineTextContent -> {
                value.commonAttributes?.writeCommonAttributes(out)
                out.name("text").value(value.text)
                out.name("type").value(AtomText.TYPE_TEXT)
            }

            is AtomContent.InlineHtmlContent -> {
                value.commonAttributes?.writeCommonAttributes(out)
                out.name("text").value(value.html)
                out.name("type").value(AtomText.TYPE_HTML)
            }

            is AtomContent.InlineXHTMLContent -> {
                value.commonAttributes?.writeCommonAttributes(out)
                out.name("xhtmlDiv").value(value.xhtmlDiv)
                out.name("type").value(AtomText.TYPE_XHTML)
            }

            is AtomContent.InlineOtherContent -> {
                value.commonAttributes?.writeCommonAttributes(out)
                out.name("text").value(value.text)
                out.name("type").value(value.mimeType)
            }

            is AtomContent.MediaContent -> {
                value.commonAttributes?.writeCommonAttributes(out)
                out.name("src").value(value.src)
                out.name("type").value(value.mimeType)
            }
        }
        out.endObject()
    }

    private fun AtomCommonAttributes.writeCommonAttributes(writer: JsonWriter) {
        writer.name(AtomCommonAttributesAdapter.COMMON_KEY)
        commonAttributesAdapter.write(writer, this)
    }

    override fun read(reader: JsonReader): AtomContent {
        var commonAttributes: AtomCommonAttributes? = null
        var type: String? = null
        var src: String? = null
        var text: String? = null
        var xhtmlDiv: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                AtomCommonAttributesAdapter.COMMON_KEY -> {
                    commonAttributes = commonAttributesAdapter.read(reader)
                }

                "type" -> {
                    type = reader.nextString()
                }

                "src" -> {
                    src = reader.nextString()
                }

                "text" -> {
                    text = reader.nextString()
                }

                "xhtmlDiv" -> {
                    xhtmlDiv = reader.nextString()
                }

                else -> reader.skipValue()
            }
        }
        reader.endObject()
        return dealContent(commonAttributes, type, src, text, xhtmlDiv)
    }

    private fun dealContent(
        commonAttributes: AtomCommonAttributes?, type: String?, src: String?, text: String?, xhtml: String?
    ): AtomContent {
        return AtomContent.create(commonAttributes, type, src, text, xhtml)
    }
}