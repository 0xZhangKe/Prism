package com.zhangke.atom.attributes

import com.google.gson.annotations.JsonAdapter
import com.zhangke.atom.adapters.AtomIdAdapter

/**
 * The "atom:id" element conveys a permanent, universally unique identifier for an entry or feed.
 * Its content MUST be an IRI, as defined by RFC3987.
 * Note that the definition of "IRI" excludes relative references.
 * Though the IRI might use a dereferencable scheme, Atom Processors MUST NOT assume it can be dereferenced.
 */
@JsonAdapter(AtomIdAdapter::class)
data class AtomId(
    override val base: String?,
    override val lang: String?,
    val uri: String,
) : AtomCommonAttributes
