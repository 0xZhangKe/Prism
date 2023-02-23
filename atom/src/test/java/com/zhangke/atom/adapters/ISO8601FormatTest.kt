package com.zhangke.atom.adapters

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.text.SimpleDateFormat

class ISO8601FormatTest {

    @Test
    fun toDate() {
        val dateTime = "2023-02-23T00:00:00+00:00"
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = ISO8601Format.toDate(dateTime)
        println(date.time)
        println(format.format(date))
        println(ISO8601Format.toDate(ISO8601Format.fromDate(date)).time)
    }

    @Test
    fun fromDate() {
    }
}