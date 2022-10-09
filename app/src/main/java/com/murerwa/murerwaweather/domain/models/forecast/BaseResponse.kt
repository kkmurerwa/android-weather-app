package com.murerwa.murerwaweather.domain.models.forecast

import com.murerwa.murerwaweather.domain.models.forecast.WeatherForecast

data class BaseResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherForecast>
)