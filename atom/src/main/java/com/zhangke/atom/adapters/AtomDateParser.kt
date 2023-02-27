package com.zhangke.atom.adapters

import org.joda.time.format.ISODateTimeFormat
import java.text.SimpleDateFormat
import java.util.*

object AtomDateParser {

    private val gmtFormat = ThreadLocal.withInitial {
        SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
            .apply {
                timeZone = TimeZone.getTimeZone("GMT")
            }
    }.get()

    fun toDate(datetime: String): Date {
        return if (datetime.endsWith("GMT")) {
            gmtFormat.parse(datetime)
        }else{
            ISO8601Format.toDate(datetime)
        }
    }

    fun fromDate(date: Date): String {
        return ISODateTimeFormat.dateTime().print(date.time)
    }
}