package com.zhangke.rss.elements

/**
 * The rss element is the top-level element of an RSS feed.
 * A feed that conforms to the RSS specification MUST contain a version attribute with the value "2.0".
 *
 * This element is REQUIRED and MUST contain a channel element.
 * The rss element MUST NOT contain more than one channel.
 */
data class Rss(
    val version: String,
    val channel: Channel,
)