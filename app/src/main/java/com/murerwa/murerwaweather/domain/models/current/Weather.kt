package com.murerwa.murerwaweather.domain.models.current

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)