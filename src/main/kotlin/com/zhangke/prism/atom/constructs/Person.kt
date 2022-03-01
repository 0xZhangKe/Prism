package com.zhangke.prism.atom.constructs

/**
 *  A Person construct is an element that describes a person, corporation, or similar entity (hereafter, 'person').
 */
data class Person(
    val name: String,
    val uri: String,
    val email: String,
): Common()

