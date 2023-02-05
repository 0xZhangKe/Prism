package com.zhangke.atom.elements

import com.zhangke.atom.attributes.CommonAttributes
import com.zhangke.atom.constructs.AtomPerson
import com.zhangke.atom.metadata.AtomCategory

data class Entry(
    val commonAttrs: CommonAttributes?,
    val atomAuthor: List<AtomPerson>?,
    val atomCategory: List<AtomCategory>?,
)
