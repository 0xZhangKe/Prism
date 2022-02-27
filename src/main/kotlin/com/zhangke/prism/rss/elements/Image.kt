package com.zhangke.prism.rss.elements

/**
 * The image element supplies a graphical logo for the feed (OPTIONAL).
 * Created by ZhangKe on 2022/2/27.
 */
data class Image(
    /**
     * is the URL of the site, when the channel is rendered, the image is a link to the site.
     */
    val link: String,
    val title: String,
    /**
     * is the URL of a GIF, JPEG or PNG image that represents the channel.
     */
    val url: String,
    val description: String?,
    /**
     * Maximum value for height is 400, default value is 31.
     */
    val height: Int = 31,
    /**
     * Maximum value for width is 144, default value is 88.
     */
    val width: Int = 88,
)


