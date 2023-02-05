package com.zhangke.atom.constructs

import com.zhangke.atom.attributes.CommonAttributes

/**
 * A Text construct contains human-readable text, usually in small quantities.
 * The content of Text constructs is Language-Sensitive.
 */
data class AtomText(
    val commonAttrs : CommonAttributes?,
    val type: String = TYPE_TEXT,
    val value: String?,
) {

    companion object {
        /**
         * If the value is "text", the content of the Text construct MUST NOT contain child elements.
         * Such text is intended to be presented to humans in a readable fashion.
         * Thus, Atom Processors MAY collapse white space (including line breaks) and
         * display the text using typographic techniques such as justification and proportional fonts.
         */
        const val TYPE_TEXT = "text"

        /**
         * If the value of "type" is "html", the content of the Text construct MUST NOT contain child elements and SHOULD be suitable for handling as HTML [HTML].
         * Any markup within MUST be escaped; for example, "<br>" as "&lt;br>".
         * HTML markup within SHOULD be such that it could validly appear directly within an HTML <DIV> element, after unescaping.
         * Atom Processors that display such content MAY use that markup to aid in its display.
         * <title type="html">
         *    Less: &lt;em> &amp;lt; &lt;/em>
         * </title>
         */
        const val TYPE_HTML = "html"

        /**
         * If the value of "type" is "xhtml", the content of the Text construct MUST be a single XHTML div element [XHTML] and SHOULD be suitable for handling as XHTML.
         * The XHTML div element itself MUST NOT be considered part of the content.
         * Atom Processors that display the content MAY use the markup to aid in displaying it.
         * The escaped versions of characters such as "&" and ">" represent those characters, not markup.
         * [See](https://datatracker.ietf.org/doc/html/rfc4287#section-3.1.1.3)
         */
        const val TYPE_XHTML = "xhtml"
    }
}