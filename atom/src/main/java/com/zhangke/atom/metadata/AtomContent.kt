package com.zhangke.atom.metadata

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