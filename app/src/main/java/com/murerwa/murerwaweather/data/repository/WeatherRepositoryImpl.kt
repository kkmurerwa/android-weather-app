package com.murerwa.murerwaweather.data.repository

import com.murerwa.murerwaweather.data.network.ApiClient
import com.murerwa.murerwaweather.data.network.BaseRepository
import com.murerwa.murerwaweather.data.network.NetworkResult
import com.murerwa.murerwaweather.domain.models.BaseResponse
import com.murerwa.murerwaweather.domain.models.current.CurrentWeather
import com.murerwa.murerwaweather.domain.models.forecast.WeatherForecast
import com.murerwa.murerwaweather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val apiClient: ApiClient
): WeatherRepository, BaseRepository() {
    override suspend fun getWeatherByCityName(cityName: String): NetworkResult<CurrentWeather> {
        return safeApiCall { apiClient.getCurrentWeather(searchKey = cityName) }
    }

    override suspend fun getFiveDayWeatherForecast(cityName: String): NetworkResult<BaseResponse<WeatherForecast>> {
        return safeApiCall { apiClient.getFiveDayWeather(searchKey = cityName) }
    }

}