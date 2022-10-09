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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murerwa.murerwaweather.R

@Composable
fun HourlyWeatherItem(
    time: String,
    temperature: String,
    weatherIcon: Int
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
        ) {
            Column(
                modifier = Modifier.weight(1.0f)
                    .padding(horizontal = 16.dp, vertical = 2.dp),
                horizontalAlignment = Alignment.Start
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
                        value = "40%",
                        modifier = Modifier.weight(1.0f)
                    )
                    WeatherItem(
                        icon = R.drawable.ic_wind_speed,
                        label = "Wind Speed",
                        value = "3.75 km/h",
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
                        value = "1019 hPa",
                        modifier = Modifier.weight(1.0f)
                    )
                    WeatherItem(
                        icon = R.drawable.ic_cloud_cover,
                        label = "Cloud Cover",
                        value = "40%",
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
                    text = "$temperature/$temperature",
                    fontSize = 18.sp
                )
            }
        }
    }
}