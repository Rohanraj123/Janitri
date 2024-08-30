package com.anim.janitri.presentation.color_list_screen.components

import android.app.AlertDialog
import android.content.Context
import com.anim.janitri.domain.model.ColorSchemeDto
import com.anim.janitri.presentation.color_list_screen.ColorListScreenViewModel
import kotlin.random.Random

fun onAddButtonClicked(
    viewModel: ColorListScreenViewModel,
    date: Long
) {
    val randomColorHex = generateRandomColor()
    val newColor = ColorSchemeDto(0, randomColorHex, date, false)
    viewModel.insertColor(newColor)
}

fun generateRandomColor(): String {
    val color = List(6) {
        val hexDigits = "0123456789ABCDEF"
        hexDigits[Random.nextInt(16)]
    }.joinToString("")
    return color
}

fun openDialogBox(context: Context, onDeleteClick: () -> Unit) {
    AlertDialog.Builder(context)
        .setTitle("Do you want to delete it?")
        .setPositiveButton("Delete") {_, _ -> onDeleteClick() }
        .setNegativeButton("Cancel") {_, _ ->  }
        .create().show()
}