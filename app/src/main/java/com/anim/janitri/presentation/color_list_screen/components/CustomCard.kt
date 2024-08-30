package com.anim.janitri.presentation.color_list_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anim.janitri.domain.model.ColorSchemeDto
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun CustomCard(
    colorSchemeDto: ColorSchemeDto,
    onCardClick: () -> Unit
) {
    val colorHexCode = colorSchemeDto.colorHex

    val localDateTime = LocalDateTime.ofInstant(
        Instant
            .ofEpochMilli(
                colorSchemeDto.date),
                ZoneId.systemDefault()
    )
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val date = formatter.format(localDateTime)

    val color = try {
        Color(android.graphics.Color.parseColor("#$colorHexCode"))
    } catch (e: Exception) {
        Color.Gray // Fallback color
    }

    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(200.dp)
            .clickable { onCardClick() }
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    text = "#$colorHexCode",
                    fontSize = 17.sp,
                    color = Color.White
                )
                HorizontalDivider(
                    modifier = Modifier.padding(start = 10.dp, end = 65.dp),
                    thickness = 1.5.dp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "Created at",
                    fontSize = 13.sp,
                    color = Color.White
                )
                Text(
                    text = date,
                    fontSize = 13.sp,
                    color = Color.White
                )
            }
        }
    }
}
