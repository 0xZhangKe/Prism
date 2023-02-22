package com.zhangke.atom.metadata

import com.zhangke.atom.attributes.AtomCommonAttributes

/**
 * The "atom:category" element conveys information about a category associated with an entry or feed.
 * This specification assigns no meaning to the content (if any) of this element.
 */
data class AtomCategory(
    override val base: String?,
    override val lang: String?,
    /**
     * The "term" attribute is a string that identifies the category to which the entry or feed belongs.
     * Category elements MUST have a "term" attribute.
     */
    val term: String,
    /**
     * The "scheme" attribute is an IRI that identifies a categorization scheme.
     * Category elements MAY have a "scheme" attribute.
     */
    val scheme: String?,
    /**
     * The "label" attribute provides a human-readable label for display in end-user applications.
     * The content of the "label" attribute is Language-Sensitive.
     * Entities such as "&amp;" and "&lt;" represent their corresponding characters ("&" and "<", respectively), not markup.
     * Category elements MAY have a "label" attribute.
     */
    val label: String?
) : AtomCommonAttributes