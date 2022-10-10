package com.murerwa.murerwaweather.presentation.utils

import com.murerwa.murerwaweather.presentation.theme.BackgroundSunny
import org.junit.Assert.*
import com.murerwa.murerwaweather.R
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class VariableExtensionsKtTest {

    @Test
    fun capitalizeString() {
        assertTrue("hello".capitalizeString() == "Hello")
        assertFalse("hello".capitalizeString() == "hello")
    }

    @Test
    fun convertToString() {
        val format = "yyyy-MM-dd HH:mm:ss"
        val dummyDate = "1970-01-01 03:16:40"

        val formatter = SimpleDateFormat(format, Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        val date = formatter.parse(dummyDate)!!

        assertFalse(date.convertToString(format) == dummyDate)

        formatter.timeZone = TimeZone.getDefault()
        val localDate = formatter.parse(dummyDate)!!

        assertTrue(localDate.convertToString(format) == dummyDate)
    }

    @Test
    fun convertToDate() {
        val format = "yyyy-MM-dd HH:mm:ss"
        val dummyDate = "1970-01-01 03:16:40"

        val formatter = SimpleDateFormat(format, Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        val date = formatter.parse(dummyDate)!!

        assertTrue(dummyDate.convertToDate(format) == date)
    }

    @Test
    fun capitalizeEachWord() {
        assertTrue("hello world".capitalizeEachWord() == "Hello World")
        assertFalse("hello world".capitalizeEachWord() == "hello world")
    }

    @Test
    fun getSuitableDrawable() {
        assertTrue("Clear".getSuitableDrawable(true) == R.drawable.ic_clear_night)
        assertTrue("Clear".getSuitableDrawable(false) == R.drawable.ic_sunny)
        assertTrue("Clouds".getSuitableDrawable(true) == R.drawable.ic_partly_cloudy_night)
        assertTrue("Clouds".getSuitableDrawable(false) == R.drawable.ic_partly_cloudy)
        assertTrue("Rain".getSuitableDrawable(true) == R.drawable.ic_rainy_night)
        assertTrue("Rain".getSuitableDrawable(false) == R.drawable.ic_thunderstorm)
    }

    @Test
    fun getSuitableColor() {
        val condition = "Clear"

        assertTrue(condition.getSuitableColor() == BackgroundSunny)
    }

    @Test
    fun getDateString() {
        val today = Calendar.getInstance().time
        val format = "yyyy-MM-dd HH:mm:ss"

        assertTrue(today.getDateString(format) == "Today")

        val yesterday = Calendar.getInstance().apply {
            add(Calendar.DATE, -1)
        }.time

        assertFalse(yesterday.getDateString(format) == "Yesterday")

        val tomorrow = Calendar.getInstance().apply {
            add(Calendar.DATE, +1)
        }.time

        assertTrue(tomorrow.getDateString(format) == "Tomorrow")

        val nextWeekDate = Calendar.getInstance().apply {
            add(Calendar.DATE, +10)
        }.time

        assertFalse(nextWeekDate.getDateString(format) == "Tomorrow")
        assertFalse(nextWeekDate.getDateString(format) == "2022-10-20")

        assertTrue(nextWeekDate.getDateString("MMMM dd") == "October 20")
    }

    @Test
    fun isNight() {
        val night = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
        }.time

        assertTrue(night.isNight())

        val morning = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 6)
        }.time

        assertFalse(morning.isNight())

        val afternoon = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 12)
        }.time

        assertFalse(afternoon.isNight())

        val evening = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 18)
        }.time

        assertTrue(evening.isNight())
    }
}