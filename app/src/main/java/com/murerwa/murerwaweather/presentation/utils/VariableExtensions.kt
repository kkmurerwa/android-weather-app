package com.murerwa.murerwaweather.presentation.utils

import androidx.compose.ui.graphics.Color
import com.murerwa.murerwaweather.R
import com.murerwa.murerwaweather.presentation.theme.BackgroundRainy
import com.murerwa.murerwaweather.presentation.theme.BackgroundSunny
import com.murerwa.murerwaweather.presentation.theme.BackgroundWindy
import com.murerwa.murerwaweather.presentation.theme.MaroonPrimary
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.capitalizeString(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun Date.convertToString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun Number.toDegrees(): String {
    return "${this.toInt()}°"
}

fun String.convertToDate(format: String = "yyyy-MM-dd HH:mm:ss", locale: Locale = Locale.getDefault()): Date {
    val formatter = SimpleDateFormat(format, locale)
    formatter.timeZone = TimeZone.getTimeZone("GMT")
    return formatter.parse(this)!!
}

fun String.capitalizeEachWord(): String {
    return this.split(" ").joinToString(" ") { it.capitalizeString() }
}

fun String.getSuitableDrawable(isNight: Boolean): Int {
    return when (this) {
        "Clear" -> {
            if (isNight) {
                R.drawable.ic_clear_night
            } else {
                R.drawable.ic_sunny
            }
        }
        "Clouds" -> {
            if (isNight) {
                R.drawable.ic_partly_cloudy_night
            } else {
                R.drawable.ic_partly_cloudy
            }
        }
        else -> {
            if (isNight) {
                R.drawable.ic_rainy_night
            } else {
                R.drawable.ic_thunderstorm
            }
        }
    }
}

fun String.getSuitableColor(): Color {
    return when (this) {
        "Clear" -> {
            BackgroundSunny
        }
        "Clouds" -> {
            BackgroundWindy
        }
        else -> {
            BackgroundRainy
        }
    }
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

fun Date.isNight(): Boolean {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    return hour in 18..23 || hour in 0..5
}