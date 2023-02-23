package com.zhangke.atom.adapters

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object RFC3339Format {

    private val formatLocal = ThreadLocal.withInitial {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX'Z'")
    }

    private val format: DateFormat get() = formatLocal.get()

    fun toDate(dateTime: String): Date {
        return format.parse(dateTime)
    }

    fun fromDate(date: Date): String {
        return format.format(date)
    }
}