package com.zhangke.atom.attributes

import com.google.gson.annotations.JsonAdapter
import com.zhangke.atom.adapters.AtomLogoAdapter

/**
 * The "atom:logo" element's content is an IRI reference RFC3987 that identifies an image that provides visual identification for a feed.
 * The image SHOULD have an aspect ratio of 2 (horizontal) to 1 (vertical).
 */
@JsonAdapter(AtomLogoAdapter::class)
data class AtomLogo(
    override val base: String?,
    override val lang: String?,
    val uri: String,
) : AtomCommonAttributes