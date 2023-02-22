package com.zhangke.atom.attributes

import com.google.gson.annotations.JsonAdapter
import com.zhangke.atom.adapters.AtomIconAdapter

@JsonAdapter(AtomIconAdapter::class)
data class AtomIcon(
    override val base: String?,
    override val lang: String?,
    val uri: String,
) : AtomCommonAttributes