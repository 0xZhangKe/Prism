package com.zhangke.atom.metadata

import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.attributes.AtomIcon
import com.zhangke.atom.attributes.AtomLogo
import com.zhangke.atom.constructs.AtomDate
import com.zhangke.atom.constructs.AtomPerson
import com.zhangke.atom.constructs.AtomText
import com.zhangke.atom.attributes.AtomId
import com.zhangke.atom.attributes.AtomLink

data class AtomSource(
    val commonAttributes: AtomCommonAttributes?,
    val author: List<AtomPerson>?,
    val category: List<AtomCategory>?,
    val contributor: List<AtomPerson>?,
    val generator: AtomGenerator?,
    val icon: AtomIcon?,
    val id: AtomId?,
    val link: List<AtomLink>?,
    val logo: AtomLogo?,
    val rights: AtomText?,
    val subtitle: AtomText?,
    val title: AtomText?,
    val updated: AtomDate?,
)