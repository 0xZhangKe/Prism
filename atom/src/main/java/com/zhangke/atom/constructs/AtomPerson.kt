package com.zhangke.prism.atom.constructs

import com.zhangke.prism.atom.attributes.CommonAttributes

/**
 *  A Person construct is an element that describes a person, corporation, or similar entity (hereafter, 'person').
 */
data class AtomPerson(
    val commonAttrs : CommonAttributes?,
    val name: String,
    val uri: String,
    val email: String,
)