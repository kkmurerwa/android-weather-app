package com.murerwa.murerwaweather.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

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
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = time
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = weatherIcon),
                contentDescription = "Weather icon",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = temperature
            )
        }
    }
}