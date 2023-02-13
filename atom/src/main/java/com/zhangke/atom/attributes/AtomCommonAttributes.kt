package com.zhangke.atom.attributes

import com.google.gson.annotations.JsonAdapter
import com.zhangke.atom.adapters.AtomCommonAttributesAdapter

@JsonAdapter(AtomCommonAttributesAdapter::class)
data class AtomCommonAttributes(
    val base: String?,
    val lang: String?,
)
