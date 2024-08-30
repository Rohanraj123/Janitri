package com.anim.janitri.domain.model

data class ColorSchemeDto(
    val id: Long,
    val colorHex: String,
    val date: Long,
    val sync: Boolean
)