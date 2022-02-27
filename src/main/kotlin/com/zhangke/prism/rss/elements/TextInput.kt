package com.zhangke.prism.rss.elements

/**
 * A channel may optionally contain a <textInput> sub-element, which contains four required sub-elements.
 *
 * Created by ZhangKe on 2022/2/27.
 */
data class TextInput(
    val title: String,
    val description: String,
    val name: String,
    val link: String,
)