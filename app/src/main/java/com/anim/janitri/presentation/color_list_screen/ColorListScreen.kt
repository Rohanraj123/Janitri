package com.anim.janitri.presentation.color_list_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anim.janitri.common.Resource
import com.anim.janitri.data.dao.ColorSchemeDao
import com.anim.janitri.data.datasource.local.ColorScheme
import com.anim.janitri.domain.model.ColorSchemeDto
import com.anim.janitri.presentation.color_list_screen.components.AddColorButton
import com.anim.janitri.presentation.color_list_screen.components.CustomCard
import com.anim.janitri.presentation.color_list_screen.components.SyncButton
import com.anim.janitri.presentation.color_list_screen.components.Title
import com.anim.janitri.presentation.color_list_screen.components.onAddButtonClicked
import com.anim.janitri.presentation.color_list_screen.components.openDialogBox
import com.anim.janitri.presentation.ui.theme.Coral
import java.time.LocalDate
import java.util.Date
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorListScreen(
    colorListScreenViewModel: ColorListScreenViewModel
) {
    val date = System.currentTimeMillis()
    val colors = colorListScreenViewModel.getColors.collectAsState().value
    val unSyncedColors = colorListScreenViewModel.getUnSyncedColors.collectAsState().value
    val syncStatus = colorListScreenViewModel.syncStatus.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(syncStatus) {
        if (syncStatus is Resource.Success) {
            Toast.makeText(context, "Colors synced successfully!", Toast.LENGTH_SHORT).show()
        } else if (syncStatus is Resource.Error) {
            Toast.makeText(context, "Failed to sync colors: ${syncStatus.message}", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Coral
                ),
                title = { Title() },
                actions = {
                    SyncButton(
                        { colorListScreenViewModel.syncColors(unSyncedColors.success) },
                        unSyncedColors.success.size)
                }
            )
        }
    ) { innerPadding ->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(colors.success) { color ->
                    Log.d("LazyList", "color : ${color.colorHex}")
                    CustomCard(
                        onCardClick = {
                            openDialogBox(context) {
                                colorListScreenViewModel.deleteColor(color.id)
                            }
                        },
                        colorSchemeDto = color
                    )
                }
            }
            AddColorButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(25.dp),
                onClick = { onAddButtonClicked(colorListScreenViewModel, date) }
            )
        }
    }
}
