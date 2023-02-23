package com.zhangke.atom.adapters

import org.joda.time.format.ISODateTimeFormat
import java.util.Date

object ISO8601Format {

    private val formatLocal = ThreadLocal.withInitial {
        ISODateTimeFormat.dateTime()
    }

    private val format = formatLocal.get()!!

    fun toDate(datetime: String): Date {
        return format.parseDateTime(datetime).toDate()
    }

    fun fromDate(date: Date): String{
        return ISODateTimeFormat.dateTime().print(date.time)
    }
}