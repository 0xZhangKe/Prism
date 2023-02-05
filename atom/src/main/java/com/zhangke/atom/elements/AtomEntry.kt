package com.zhangke.atom.elements

import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.constructs.AtomDate
import com.zhangke.atom.constructs.AtomPerson
import com.zhangke.atom.constructs.AtomText
import com.zhangke.atom.metadata.AtomCategory
import com.zhangke.atom.metadata.AtomContent
import com.zhangke.atom.metadata.AtomSource

data class AtomEntry(
    val commonAttrs: AtomCommonAttributes?,
    val author: List<AtomPerson>?,
    val category: List<AtomCategory>?,
    val content: AtomContent?,
    val contributor: List<AtomPerson>?,
    val id: AtomId,
    val link: List<AtomLink>?,
    val rights: AtomText?,
    val published: AtomDate?,
    val source: AtomSource?,
    val summary: AtomText?,
    val title: AtomText,
    val updated: AtomDate,
)
