package com.mobile.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun dateSecondFormatter(time: Long): String {
    val date = Date(time * 1000L)
    val format = SimpleDateFormat("EEEE, d MMMM yyyy")
    return format.format(date)
}

fun getDayName(time: Long): String {
    val cal = Calendar.getInstance(Locale.getDefault())
    cal.timeInMillis = time * 1000L
    //    val format = SimpleDateFormat("EEEE")
//    return format.format(date)
    return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale("ID", "IDN"))
}

fun getDayNameFromDateText(dateText: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val formatter = SimpleDateFormat("EEEE")
    return formatter.format(parser.parse(dateText))
}

fun dateMillisecondFormatter(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(date)
}

fun hourSecondFormatter(time: Long): String {
    val date = Date(time * 1000)
    val format = SimpleDateFormat("hh:mm")
    return format.format(date)
}
