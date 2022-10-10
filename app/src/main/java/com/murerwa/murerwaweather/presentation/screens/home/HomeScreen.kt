package com.murerwa.murerwaweather.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel
import com.murerwa.murerwaweather.R
import com.murerwa.murerwaweather.domain.models.forecast.WeatherForecast
import com.murerwa.murerwaweather.presentation.screens.common.ErrorScreen
import com.murerwa.murerwaweather.presentation.screens.home.components.DayChip
import com.murerwa.murerwaweather.presentation.screens.home.components.HourlyWeatherItem
import com.murerwa.murerwaweather.presentation.theme.BackgroundSunny
import com.murerwa.murerwaweather.presentation.theme.Purple700
import com.murerwa.murerwaweather.presentation.utils.*
import timber.log.Timber
import java.util.Calendar

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: WeatherViewModel = getViewModel()
) {
    val weatherState = viewModel.currentWeatherResponse.value
    val fiveDayForecastState = viewModel.fiveDayForecastResponse.value

    val forecastsByDay: HashMap<String, List<WeatherForecast>> = hashMapOf()
    val days = mutableSetOf<String>()

    val bgColorState = remember {
        mutableStateOf(BackgroundSunny)
    }

    val today = Calendar.getInstance().time.convertToString("dd-MM-yyyy")

    val selectedDate = remember {
        mutableStateOf(today)
    }

    val focusManager = LocalFocusManager.current

    when (fiveDayForecastState) {
        is UIState.Loading -> {}
        is UIState.Success -> {
            val fiveDayForecast = fiveDayForecastState.value.list

            val sortedFiveDayForecast = fiveDayForecast.sortedBy { it.dt }

            sortedFiveDayForecast.forEach { forecast ->
                val currentDate = forecast.dt_txt.convertToDate()

                val date = currentDate.convertToString("dd-MM-yyyy")

                days.add(date)

                if (forecastsByDay.containsKey(date)) {
                    forecastsByDay[date] = forecastsByDay[date]!! + forecast
                } else {
                    forecastsByDay[date] = listOf(forecast)
                }
            }

            Timber.d("Days: $days")
        }
        is UIState.Error -> {}
    }

    Scaffold(
        backgroundColor = bgColorState.value,
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .background(Color.Transparent),
            ){
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val textState = remember {
                        mutableStateOf(TextFieldValue())
                    }

                    OutlinedTextField(
                        value = textState.value,
                        onValueChange = { textState.value = it },
                        modifier = Modifier.weight(1.0f),
                        shape = RoundedCornerShape(12.dp),
                        placeholder = {
                            Text(
                                text = "Search City",
                                color = Color.Gray
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "Search Icon",
                                modifier = Modifier.padding(1.dp)
                                    .width(20.dp)
                                    .height(20.dp),
                                tint = Color.Black,
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            cursorColor = Color.Gray,
                            textColor = Color.Black
                        ),
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            val searchQuery = textState.value.text

                            viewModel.getCurrentWeather(searchQuery)
                            viewModel.getFiveDayForecast(searchQuery)

                            focusManager.clearFocus()
                        },
                        modifier = Modifier.align(Alignment.CenterVertically)
                            .height(55.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Purple700
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Search",
                            modifier = Modifier.padding(5.dp),
                            color = Color.White
                        )
                    }
                }
            }
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            when (weatherState) {
                is UIState.Loading -> {
                    CircularProgressIndicator()
                }
                is UIState.Success -> {
                    val currentWeather = weatherState.value
                    val mainWeather = currentWeather.weather[0].main
                    val currentDate = Calendar.getInstance().time

                    val isCurrentlyNight = currentDate.isNight()

                    bgColorState.value = mainWeather.getSuitableColor()

                    Column(
                        modifier = Modifier.fillMaxSize()
                            .padding(10.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(18.dp))
                        Image(
                            painter = painterResource(id = mainWeather.getSuitableDrawable(isCurrentlyNight)),
                            contentDescription = "Weather Icon",
                            modifier = Modifier
                                .width(140.dp)
                                .height(140.dp)
                        )
                        Text(
                            text = currentWeather.weather[0].main,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 15.dp),
                            fontSize = 20.sp,
                        )
                        Text(
                            text = currentWeather.main.temp.toDegrees(),
                            color = Color.Black,
                            modifier = Modifier.padding(5.dp),
                            fontSize = 100.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_wind_speed),
                                contentDescription = "Wind speed Icon",
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                            )
                            Text(
                                text = "${currentWeather.wind.speed} km/h",
                                color = Color.Black,
                                modifier = Modifier.padding(5.dp),
                                fontSize = 16.sp,
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_humidity),
                                contentDescription = "Humidity Icon",
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                            )
                            Text(
                                text = "${currentWeather.main.humidity}%",
                                color = Color.Black,
                                modifier = Modifier.padding(5.dp),
                                fontSize = 16.sp,
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_temperature),
                                contentDescription = "Temperature Icon",
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                            )
                            Text(
                                text = "Feels Like ${currentWeather.main.feels_like.toDegrees()}c",
                                color = Color.Black,
                                modifier = Modifier.padding(5.dp),
                                fontSize = 15.sp,
                            )
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        ScrollableTabRow(
                            selectedTabIndex = 0,
                            backgroundColor = Color.Transparent,
                            edgePadding = 0.dp,
                            divider = {},
                            indicator = {}
                        ) {
                            when (fiveDayForecastState) {
                                is UIState.Loading -> {}
                                is UIState.Success -> {
                                    days.forEach { date ->

                                        val isSelected = selectedDate.value == date

                                        Tab(
                                            selected = isSelected,
                                            onClick = {
                                                selectedDate.value = date
                                            },
                                            modifier = Modifier.padding(5.dp)
                                        ) {
                                            DayChip(
                                                day = date.convertToDate("dd-MM-yyyy").getDateString(),
                                                isSelected = isSelected
                                            )
                                        }
                                    }
                                }
                                is UIState.Error -> {}
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Column {
                            when (fiveDayForecastState) {
                                is UIState.Loading -> {}
                                is UIState.Success -> {
                                    val todayForecast = forecastsByDay[selectedDate.value]

                                    for (item in todayForecast!!) {
                                        val currentDate = item.dt_txt.convertToDate()

                                        val currentMainWeather = item.weather[0].main
                                        val isNight = currentDate.isNight()

                                        HourlyWeatherItem(
                                            time = currentDate.convertToString("HH:mm a"),
                                            weatherIcon = currentMainWeather.getSuitableDrawable(isNight),
                                            weatherItem = item
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                    }
                                }
                                is UIState.Error -> {
                                    Text(
                                        text = fiveDayForecastState.errorMessage ?: "Error",
                                        color = Color.Red,
                                        modifier = Modifier.padding(5.dp),
                                        fontSize = 16.sp,
                                    )
                                }
                            }
                        }
                    }
                }
                is UIState.Error -> {
                    if (weatherState.isNetworkError) {
                        ErrorScreen(
                            message = "We encountered a network error. " +
                                    "Please check your internet connection and try again.",
                            imageDrawable = R.drawable.ic_error_internet
                        )
                    } else {
                        ErrorScreen(message = "Sorry. Something went wrong while loading the data.")
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}