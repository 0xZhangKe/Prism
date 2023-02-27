package com.zhangke.atom.constructs

import com.google.gson.annotations.JsonAdapter
import com.zhangke.atom.adapters.AtomPersonTypeAdapter
import com.zhangke.atom.attributes.AtomCommonAttributes

/**
 *  A Person construct is an element that describes a person, corporation, or similar entity (hereafter, 'person').
 */
@JsonAdapter(AtomPersonTypeAdapter::class)
data class AtomPerson(
    override val base: String?,
    override val lang: String?,
    val name: String,
    val uri: String?,
    val email: String?,
) : AtomCommonAttributes