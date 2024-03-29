package com.zhangke.atom.metadata

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.adapters.readCommon
import com.zhangke.atom.adapters.writeCommonAttributes
import com.zhangke.atom.adapters.writeSelfWith
import com.zhangke.atom.attributes.AtomCommonAttributes
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
sealed class AtomContent(
    override val base: String?,
    override val lang: String?
) : AtomCommonAttributes {

    data class InlineTextContent(
        private val commonAttributes: AtomCommonAttributes?,
        val text: String,
    ) : AtomContent(commonAttributes?.base, commonAttributes?.lang)

    data class InlineHtmlContent(
        private val commonAttributes: AtomCommonAttributes?,
        val html: String,
    ) : AtomContent(commonAttributes?.base, commonAttributes?.lang)

    data class InlineXHTMLContent(
        private val commonAttributes: AtomCommonAttributes?,
        val xhtmlDiv: String
    ) : AtomContent(commonAttributes?.base, commonAttributes?.lang)

    data class InlineOtherContent(
        private val commonAttributes: AtomCommonAttributes?,
        val text: String,
        val mimeType: String?,
    ) : AtomContent(commonAttributes?.base, commonAttributes?.lang)

    data class MediaContent(
        private val commonAttributes: AtomCommonAttributes?,
        val src: String,
        val mimeType: String?,
    ) : AtomContent(commonAttributes?.base, commonAttributes?.lang)

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
                    InlineTextContent(
                        commonAttributes = commonAttributes,
                        text.orEmpty()
                    )
                }

                type == AtomText.TYPE_HTML -> InlineHtmlContent(
                    commonAttributes = commonAttributes,
                    text.orEmpty()
                )

                type == AtomText.TYPE_XHTML -> InlineXHTMLContent(
                    commonAttributes = commonAttributes,
                    xhtml.orEmpty()
                )

                src != null -> MediaContent(
                    commonAttributes = commonAttributes,
                    src,
                    type
                )

                else -> InlineOtherContent(
                    commonAttributes = commonAttributes,
                    text.orEmpty(),
                    type
                )
            }
        }
    }
}

internal class AtomContentJsonAdapter : TypeAdapter<AtomContent>() {

    override fun write(out: JsonWriter, value: AtomContent) {
        with(out) {
            beginObject()
            when (value) {
                is AtomContent.InlineTextContent -> {
                    value.writeCommonAttributes()
                    value.text.writeSelfWith("text")
                    AtomText.TYPE_TEXT.writeSelfWith("type")
                }

                is AtomContent.InlineHtmlContent -> {
                    value.writeCommonAttributes()
                    value.html.writeSelfWith("text")
                    AtomText.TYPE_HTML.writeSelfWith("type")
                }

                is AtomContent.InlineXHTMLContent -> {
                    value.writeCommonAttributes()
                    value.xhtmlDiv.writeSelfWith("xhtmlDiv")
                    AtomText.TYPE_XHTML.writeSelfWith("type")
                }

                is AtomContent.InlineOtherContent -> {
                    value.writeCommonAttributes()
                    value.text.writeSelfWith("text")
                    value.mimeType?.writeSelfWith("type")
                }

                is AtomContent.MediaContent -> {
                    value.writeCommonAttributes()
                    value.src.writeSelfWith("src")
                    value.mimeType?.writeSelfWith("type")
                }
            }
            out.endObject()
        }
    }

    override fun read(reader: JsonReader): AtomContent {
        var commonAttributes: AtomCommonAttributes? = null
        var type: String? = null
        var src: String? = null
        var text: String? = null
        var xhtmlDiv: String? = null
        commonAttributes = reader.readCommon { name, _ ->
            when (name) {
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
        return dealContent(commonAttributes, type, src, text, xhtmlDiv)
    }

    private fun dealContent(
        commonAttributes: AtomCommonAttributes?, type: String?, src: String?, text: String?, xhtml: String?
    ): AtomContent {
        return AtomContent.create(commonAttributes, type, src, text, xhtml)
    }
}