package com.murerwa.murerwaweather.domain.repository

import com.murerwa.murerwaweather.data.network.NetworkResult
import com.murerwa.murerwaweather.domain.models.forecast.BaseResponse
import com.murerwa.murerwaweather.domain.models.current.CurrentWeather

interface WeatherRepository {
    suspend fun getWeatherByCityName(cityName: String): NetworkResult<CurrentWeather>
    suspend fun getFiveDayWeatherForecast(cityName: String): NetworkResult<BaseResponse>
}