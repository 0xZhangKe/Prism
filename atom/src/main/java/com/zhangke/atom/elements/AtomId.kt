package com.zhangke.atom.elements

import com.zhangke.atom.attributes.AtomCommonAttributes

/**
 * The "atom:id" element conveys a permanent, universally unique identifier for an entry or feed.
 * Its content MUST be an IRI, as defined by RFC3987.
 * Note that the definition of "IRI" excludes relative references.
 * Though the IRI might use a dereferencable scheme, Atom Processors MUST NOT assume it can be dereferenced.
 */
data class AtomId(
    val commonAttrs: AtomCommonAttributes?,
    val value: String,
)
