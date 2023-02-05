package com.zhangke.atom.constructs

import com.zhangke.atom.attributes.CommonAttributes

/**
 *  A Person construct is an element that describes a person, corporation, or similar entity (hereafter, 'person').
 */
data class AtomPerson(
    val commonAttrs : CommonAttributes?,
    val name: String,
    val uri: String,
    val email: String,
)