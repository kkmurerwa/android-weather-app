package com.murerwa.murerwaweather.data.network

import com.murerwa.murerwaweather.domain.models.BaseResponse
import com.murerwa.murerwaweather.domain.models.current.CurrentWeather
import com.murerwa.murerwaweather.domain.models.forecast.WeatherForecast
import retrofit2.http.*

interface ApiClient {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("appid") apiKey: String = ApiKey.API_KEY,
        @Query("units") units: String = Constants.UNITS,
        @Query("q") searchKey: String
    ): CurrentWeather

    @GET("forecast")
    suspend fun getFiveDayWeather(
        @Query("appid") apiKey: String = ApiKey.API_KEY,
        @Query("units") units: String = Constants.UNITS,
        @Query("q") searchKey: String
    ): BaseResponse<WeatherForecast>

}