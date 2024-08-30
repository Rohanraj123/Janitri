package com.anim.janitri.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anim.janitri.common.Screen
import com.anim.janitri.presentation.color_list_screen.ColorListScreen
import com.anim.janitri.presentation.color_list_screen.ColorListScreenViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val colorListScreenViewModel = hiltViewModel<ColorListScreenViewModel>()

    NavHost(navController = navController, startDestination = Screen.ColorListScreen.routes) {

        composable(route = Screen.ColorListScreen.routes) {
            ColorListScreen(colorListScreenViewModel)
        }
    }
}