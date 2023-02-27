package com.zhangke.atom.adapters

import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import java.util.Date

object ISO8601Format {

    fun toDate(datetime: String): Date {
        return DateTime.parse(datetime).toDate()
    }

    fun fromDate(date: Date): String{
        return ISODateTimeFormat.dateTime().print(date.time)
    }
}