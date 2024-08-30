package com.anim.janitri.presentation.color_list_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anim.janitri.R
import com.anim.janitri.presentation.ui.theme.IceBlue
import com.anim.janitri.presentation.ui.theme.Turquoise

@Composable
fun SyncButton(
    onClick: () -> Unit,
    unSyncedColor: Int = 0
) {
    Button(
        modifier = Modifier.wrapContentWidth(),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Turquoise
        ),
        shape = RoundedCornerShape(50.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$unSyncedColor",
                fontSize = 20.sp,
                color = Color.White
            )
            Icon(
                painter = painterResource(id = R.drawable.sync_icon_blue),
                contentDescription = "Sync icon",
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        }
    }
}