package com.murerwa.murerwaweather.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murerwa.murerwaweather.domain.models.BaseResponse
import com.murerwa.murerwaweather.domain.models.current.CurrentWeather
import com.murerwa.murerwaweather.domain.models.forecast.WeatherForecast
import com.murerwa.murerwaweather.domain.repository.WeatherRepository
import com.murerwa.murerwaweather.presentation.utils.UIState
import com.murerwa.murerwaweather.presentation.utils.convertToUIState
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _currentWeatherResponse: MutableState<UIState<CurrentWeather>> = mutableStateOf(
        UIState.Loading)
    val currentWeatherResponse = _currentWeatherResponse

    private val _fiveDayForecastResponse: MutableState<UIState<BaseResponse<WeatherForecast>>> = mutableStateOf(UIState.Loading)
    val fiveDayForecastResponse = _fiveDayForecastResponse

    fun getCurrentWeather(searchQuery: String) = viewModelScope.launch {
        _currentWeatherResponse.value = UIState.Loading
        val response = weatherRepository.getWeatherByCityName(searchQuery)

        _currentWeatherResponse.value = convertToUIState(response)
    }

    fun getFiveDayForecast(searchQuery: String) = viewModelScope.launch {
        _fiveDayForecastResponse.value = UIState.Loading
        val response = weatherRepository.getFiveDayWeatherForecast(searchQuery)

        _fiveDayForecastResponse.value = convertToUIState(response)
    }
}