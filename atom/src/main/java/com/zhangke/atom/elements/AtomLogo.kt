package com.zhangke.prism.atom.elements

import com.zhangke.prism.atom.attributes.CommonAttributes

/**
 * The "atom:logo" element's content is an IRI reference RFC3987 that identifies an image that provides visual identification for a feed.
 * The image SHOULD have an aspect ratio of 2 (horizontal) to 1 (vertical).
 */
data class AtomLogo(
    val commonAttrs: CommonAttributes?,
    val value: String,
)
