package com.murerwa.murerwaweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.murerwa.murerwaweather.presentation.navigation.SetUpNavGraph
import com.murerwa.murerwaweather.presentation.screens.home.WeatherViewModel
import com.murerwa.murerwaweather.presentation.theme.MurerwaWeatherTheme
import com.murerwa.murerwaweather.presentation.utils.UIState
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MurerwaWeatherTheme {
                val navController = rememberNavController()

                SetUpNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    viewModel: WeatherViewModel = getViewModel()
) {
    Text(text = "Hello $name!")

//    viewModel.getCurrentWeather("Nairobi")

    when (val state = viewModel.currentWeatherResponse.value) {
        is UIState.Success -> {
            Timber.d("Success ${state.value}")
        }
        is UIState.Error -> {
            Timber.d("Error: ${state.errorMessage}")
        }
        is UIState.Loading -> {
            Timber.d("Loading")
        }
    }

//    viewModel.getFiveDayForecast("Nairobi")

    when (val fiveDayForecastState = viewModel.fiveDayForecastResponse.value) {
        is UIState.Success -> {
            Timber.d("Five day forecast is ${fiveDayForecastState.value}")
        }
        is UIState.Error -> {
            Timber.d("Five day forecast error is ${fiveDayForecastState.errorMessage}")
        }
        is UIState.Loading -> {
            Timber.d("Loading")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MurerwaWeatherTheme {
        Greeting("Android")
    }
}