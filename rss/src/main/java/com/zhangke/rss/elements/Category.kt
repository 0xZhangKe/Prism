package com.zhangke.rss.elements

/**
 * An item's category element identifies a category or tag to which the item belongs (OPTIONAL).
 * This element MAY include a domain attribute that identifies the category's taxonomy.
 * Created by ZhangKe on 2022/2/27.
 */
data class Category(
    val value: String,
    val domain: String?
)
