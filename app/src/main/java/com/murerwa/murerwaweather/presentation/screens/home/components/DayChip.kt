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
import com.murerwa.murerwaweather.presentation.theme.Purple700

@Composable
fun DayChip(
    day: String,
    isSelected: Boolean = false
) {
    Card(
        backgroundColor = if (isSelected) Purple700 else Color.White,
        elevation = 0.dp,
        shape = RoundedCornerShape(25.dp),
    ) {
        Text(
            text = day,
            fontSize = 14.sp,
            color = if (isSelected) Color.White else Color.Black,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
        )
    }
}