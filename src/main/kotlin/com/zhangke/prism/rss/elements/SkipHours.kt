package com.zhangke.prism.rss.elements

/**
 * The channel's skipHours element identifies the hours of the day during which the feed is not updated (OPTIONAL).
 * This element contains individual hour elements identifying the hours to skip.
 * Created by ZhangKe on 2022/2/27.
 */
data class SkipHours(
    /**
     * The hour element identifies an hour of the day in Greenwich Mean Time (GMT) (REQUIRED). The hour MUST be expressed as an integer representing the number of hours since 00:00:00 GMT.
     * Values from 0 to 23 are permitted, with 0 representing midnight. An hour MUST NOT be duplicated.
     */
    val hours: List<Int>
)
