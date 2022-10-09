package com.murerwa.murerwaweather.presentation.screens.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DayChip(
    day: String,
    color: Color = Color.White
) {
    Card(
        backgroundColor = color,
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(
            text = day,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
        )
    }
}