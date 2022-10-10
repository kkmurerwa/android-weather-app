package com.murerwa.murerwaweather.data.network

import com.murerwa.murerwaweather.presentation.utils.capitalizeString
import okhttp3.ResponseBody
import org.json.JSONObject
import java.util.*

fun ResponseBody?.readError(): String? {
    if (this == null) return null
    return try {
        val returnStringError: String
        val jsonObj = JSONObject(this.charStream().readText())
        returnStringError = if (jsonObj.has("message")) {
            jsonObj.getString("message")
        } else {
            jsonObj.toString()
        }
        returnStringError.trim()
    } catch (_: Exception) {
        null
    }
}