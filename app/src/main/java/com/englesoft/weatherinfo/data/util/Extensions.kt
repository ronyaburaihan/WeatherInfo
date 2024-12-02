package com.englesoft.weatherinfo.data.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

fun Double.kelvinToCelsius(): Int {
    return (this - 273.15).toInt()
}

fun Int.unixTimestampToDateTimeString(): String = runCatching {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@unixTimestampToDateTimeString * 1000L
    }
    SimpleDateFormat("dd MMM, yyyy - hh:mm a", Locale.ENGLISH).apply {
        timeZone = TimeZone.getDefault()
    }.format(calendar.time)
}.getOrDefault(this.toString())

fun Int.unixTimestampToTimeString(): String = runCatching {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@unixTimestampToTimeString * 1000L
    }
    SimpleDateFormat("hh:mm a", Locale.ENGLISH).apply {
        timeZone = TimeZone.getDefault()
    }.format(calendar.time)
}.getOrDefault(this.toString())