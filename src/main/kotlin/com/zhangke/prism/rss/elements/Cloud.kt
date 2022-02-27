package com.zhangke.prism.rss.elements

/**
 * The cloud element indicates that updates to the feed can be monitored using a web service that implements the RssCloud application programming interface (OPTIONAL).
 * Created by ZhangKe on 2022/2/27.
 */
data class Cloud(
    val domain: String,
    val path: String,
    val port: String,
    val protocol: String,
    val registerProcedure: String?,
)
