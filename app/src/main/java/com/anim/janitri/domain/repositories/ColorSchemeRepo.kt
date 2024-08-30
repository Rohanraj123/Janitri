package com.anim.janitri.domain.repositories

import com.anim.janitri.domain.model.ColorSchemeDto
import kotlinx.coroutines.flow.Flow

interface ColorSchemeRepo {

    suspend fun insertColor(colorSchemeDto: ColorSchemeDto)

    suspend fun deleteColor(id: Long)

    fun getColors(): Flow<List<ColorSchemeDto>>

    fun getUnSyncedColors(): Flow<List<ColorSchemeDto>>

    suspend fun updateSyncStatus(ids: List<Long>)
}