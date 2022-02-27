package com.zhangke.prism.rss.elements

/**
 * The channel element describes the RSS feed, providing such information as its title and description,
 * and contains items that represent discrete updates to the web content represented by the feed.
 * Created by ZhangKe on 2022/2/27.
 */
data class Channel(
    val title: String,
    val link: String,
    val description: String,
    val category: List<Category>?,
    val cloud: Cloud?,
    /**
     * Copyright notice for content in the channel.
     */
    val copyright: String?,
    /**
     * Email address for person responsible for editorial content.
     */
    val managingEditor: String?,
    /**
     * Email address for person responsible for technical issues relating to channel.
     */
    val webMaster: String?,
    /**
     * 	A URL that points to the documentation for the format used in the RSS file.
     * 	It's probably a pointer to this page. It's for people who might stumble across an RSS file on a Web server 25 years from now and wonder what it is.
     */
    val docs: String?,
    /**
     * A string indicating the program used to generate the channel.
     */
    val generator: String?,
    val image: Image?,
    /**
     * The language the channel is written in. This allows aggregators to group all Italian language sites, for example, on a single page.
     * A list of allowable values for this element, as provided by Netscape, is here. You may also use values defined by the W3C.
     */
    val language: String?,
    /**
     * The last time the content of the channel changed.
     */
    val lastBuildDate: String?,
    /**
     * The publication date for the content in the channel.
     * For example, the New York Times publishes on a daily basis, the publication date flips once every 24 hours.
     * That's when the pubDate of the channel changes.
     * All date-times in RSS conform to the Date and Time Specification of RFC 822, with the exception that the year may be expressed with two characters or four characters (four preferred).
     */
    val pubDate: String?,
    /**
     * The PICS rating for the channel.
     */
    val rating: String?,
    val skipDays: SkipDays?,
    val skipHours: SkipHours?,
    val textInput: TextInput?,
    /**
     * ttl stands for time to live. It's a number of minutes that indicates how long a channel can be cached before refreshing from the source.
     * This makes it possible for RSS sources to be managed by a file-sharing network such as Gnutella.
     */
    val ttl: String?,
    val items: List<Item>?,
)