package com.zhangke.prism.sample

import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    parseGMTDateTime()
}

private fun parseGMTDateTime(){
    val dateTimeString = "Tue, 21 Feb 2023 18:33:33 GMT"
    val format = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
    format.timeZone = TimeZone.getTimeZone("GMT")
    val date = format.parse(dateTimeString)
    println(date.time)
}

private fun validateJodaTime(){
    val datetime = "2023-02-23T00:00:00+00:00"
    val date = DateTime.parse(datetime).toDate()
    println(date.time)
    println(ISODateTimeFormat.dateTime().print(date.time))
}