package com.anim.janitri.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "color_scheme")
data class ColorScheme(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val colorHex: String,
    val date: Long,
    val sync: Boolean
)
