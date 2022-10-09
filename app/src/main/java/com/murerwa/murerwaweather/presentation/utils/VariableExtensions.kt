package com.murerwa.murerwaweather.presentation.utils

import java.text.NumberFormat
import java.util.*

fun String.getYear() = this.substring(0, 4)

fun String.capitalizeString(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun Number.commaSeparated(): String {
    return NumberFormat.getNumberInstance(Locale.US).format(this)
}