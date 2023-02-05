package com.zhangke.atom.metadata

import com.zhangke.atom.attributes.CommonAttributes

data class AtomCategory(
    val commonAttrs: CommonAttributes?,
    val term: String,
    val scheme: String?,
    val label: String?
)