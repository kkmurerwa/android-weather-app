package com.murerwa.murerwaweather.presentation.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.getYear() = this.substring(0, 4)

fun String.capitalizeString(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun Number.commaSeparated(): String {
    return NumberFormat.getNumberInstance(Locale.US).format(this)
}

fun Number.toDegrees(): String {
    return "${this.toInt()}°"
}

fun Date.convertToString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}
fun String.convertToDate(format: String = "yyyy-MM-dd HH:mm:ss", locale: Locale = Locale.getDefault()): Date {
    val formatter = SimpleDateFormat(format, locale)
    formatter.timeZone = TimeZone.getTimeZone("GMT")
    return formatter.parse(this)!!
}

fun String.capitalizeEachWord(): String {
    return this.split(" ").joinToString(" ") { it.capitalizeString() }
}

fun Date.getDateString(dateFormat: String = "MMMM dd"): String {
    val currentDate = Calendar.getInstance()

    val tomorrowDate = Calendar.getInstance()
    tomorrowDate.add(Calendar.DATE, +1)

    return when (this.convertToString(dateFormat)) {
        currentDate.time.convertToString(dateFormat) -> {
            "Today"
        }
        tomorrowDate.time.convertToString(dateFormat) -> {
            "Tomorrow"
        }
        else -> {
            this.convertToString(dateFormat)
        }
    }
}