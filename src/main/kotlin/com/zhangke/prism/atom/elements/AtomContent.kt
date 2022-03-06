package com.zhangke.prism.atom.elements

import com.zhangke.prism.atom.attributes.CommonAttributes

data class AtomContent(
    val commonAttrs: CommonAttributes?,
    /**
     * On the atom:content element, the value of the "type" attribute MAY be one of "text", "html", or "xhtml".
     * Failing that, it MUST conform to the syntax of a MIME media type, but MUST NOT be a composite type (see Section 4.2.6 of MIMEREG).
     * If neither the type attribute nor the src attribute is provided, Atom Processors MUST behave as though the type attribute were present with a value of "text".
     */
    val type: String = TYPE_TEXT,
    /**
     * atom:content MAY have a "src" attribute, whose value MUST be an IRI reference [RFC3987].
     * If the "src" attribute is present, atom:content MUST be empty.
     * Atom Processors MAY use the IRI to retrieve the content and MAY choose to ignore remote content or to present it in a different manner than local content.
     */
    val src: String?,
) {

    companion object {
        const val TYPE_TEXT = "text"

        const val TYPE_HTML = "html"

        const val TYPE_XHTML = "xhtml"
    }
}
