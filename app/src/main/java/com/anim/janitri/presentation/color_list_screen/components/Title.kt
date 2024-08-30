package com.anim.janitri.presentation.color_list_screen.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Title() {
    Text(
        text = "Color App",
        fontWeight = FontWeight.Normal,
        color = Color.White
    )
}