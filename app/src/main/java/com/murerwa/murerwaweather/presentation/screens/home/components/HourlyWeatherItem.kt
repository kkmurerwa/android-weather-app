package com.murerwa.murerwaweather.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murerwa.murerwaweather.R
import com.murerwa.murerwaweather.domain.models.forecast.WeatherForecast
import com.murerwa.murerwaweather.presentation.utils.capitalizeEachWord

@Composable
fun HourlyWeatherItem(
    time: String,
    weatherIcon: Int,
    weatherItem: WeatherForecast
) {
    Card(
        backgroundColor = Color.White,
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1.0f)
                    .padding(horizontal = 16.dp, vertical = 2.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = time,
                    color = Color.Black,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    WeatherItem(
                        icon = R.drawable.ic_humidity,
                        label = "Humidity",
                        value = "${weatherItem.main.humidity} %",
                        modifier = Modifier.weight(1.0f)
                    )
                    WeatherItem(
                        icon = R.drawable.ic_wind_speed,
                        label = "Wind Speed",
                        value = "${weatherItem.wind.speed} km/h",
                        modifier = Modifier.weight(1.0f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    WeatherItem(
                        icon = R.drawable.ic_air_pressure,
                        label = "Air Pressure",
                        value = "${weatherItem.main.pressure} hPa",
                        modifier = Modifier.weight(1.0f)
                    )
                    WeatherItem(
                        icon = R.drawable.ic_cloud_cover,
                        label = "Cloud Cover",
                        value = "${weatherItem.clouds.all}%",
                        modifier = Modifier.weight(1.0f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = weatherIcon),
                    contentDescription = "Weather icon",
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp)
                )
                Text(
                    text = weatherItem.weather[0].description.capitalizeEachWord(),
                    color = Color.Gray,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 12.sp
                )
                Text(
                    text = "${weatherItem.main.temp} °C",
                    fontSize = 18.sp
                )
            }
        }
    }
}