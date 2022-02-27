package com.zhangke.prism.rss.elements

/**
 * The channel's skipDays element identifies days of the week during which the feed is not updated (OPTIONAL).
 * This element contains up to seven day elements identifying the days to skip.
 * Created by ZhangKe on 2022/2/27.
 */
data class SkipDays(
    /**
     * The day element identifies a weekday in Greenwich Mean Time (GMT) (REQUIRED).
     * Seven values are permitted -- "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" or "Sunday" -- and MUST NOT be duplicated.
     */
    val days: List<String>
)
