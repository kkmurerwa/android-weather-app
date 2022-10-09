package com.murerwa.murerwaweather.presentation.di

import com.murerwa.murerwaweather.presentation.screens.home.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = listOf(
    module {
        viewModel { WeatherViewModel(get()) }
    }
)