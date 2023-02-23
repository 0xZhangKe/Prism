package com.zhangke.atom.elements

import com.google.gson.annotations.JsonAdapter
import com.zhangke.atom.adapters.AtomCategoryListAdapter
import com.zhangke.atom.adapters.AtomLinkListAdapter
import com.zhangke.atom.adapters.AtomPersonListAdapter
import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.attributes.AtomId
import com.zhangke.atom.attributes.AtomLink
import com.zhangke.atom.constructs.AtomDate
import com.zhangke.atom.constructs.AtomPerson
import com.zhangke.atom.constructs.AtomText
import com.zhangke.atom.metadata.AtomCategory
import com.zhangke.atom.metadata.AtomContent
import com.zhangke.atom.metadata.AtomSource

data class AtomEntry(
    override val base: String?,
    override val lang: String?,
    @JsonAdapter(AtomPersonListAdapter::class)
    val author: List<AtomPerson>?,
    @JsonAdapter(AtomCategoryListAdapter::class)
    val category: List<AtomCategory>?,
    val content: AtomContent?,
    @JsonAdapter(AtomPersonListAdapter::class)
    val contributor: List<AtomPerson>?,
    val id: AtomId,
    @JsonAdapter(AtomLinkListAdapter::class)
    val link: List<AtomLink>?,
    val rights: AtomText?,
    val published: AtomDate?,
    val source: AtomSource?,
    val summary: AtomText?,
    val title: AtomText,
    val updated: AtomDate,
) : AtomElement, AtomCommonAttributes
