package com.anim.janitri.common

sealed class Screen(val routes: String) {
    data object ColorListScreen : Screen(Routes.ColorListScreen.name)
}