package com.zhangke.rss.rss.elements

/**
 * A channel may contain any number of <item>s.
 * An item may represent a "story" -- much like a story in a newspaper or magazine;
 * if so its description is a synopsis of the story, and the link points to the full story.
 * An item may also be complete in itself, if so, the description contains the text (entity-encoded HTML is allowed; see examples),
 * and the link and title may be omitted. All elements of an item are optional, however at least one of title or description must be present.
 *
 * Created by ZhangKe on 2022/2/27.
 */
data class Item(
    val title: String,
    val link: String,
    val description: String?,
    val author: String?,
    val category: List<Category>?,
    val comments: String?,
    val enclosure: String?,
    val guid: String?,
    val pubDate: String?,
    val source: String?,
)