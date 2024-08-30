package com.anim.janitri.data.repositories

import com.anim.janitri.data.dao.ColorSchemeDao
import com.anim.janitri.data.datasource.local.ColorScheme
import com.anim.janitri.domain.model.ColorSchemeDto
import com.anim.janitri.domain.repositories.ColorSchemeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ColorSchemeRepoImpl @Inject constructor(
    private val colorSchemeDao: ColorSchemeDao
) : ColorSchemeRepo {

    override suspend fun insertColor(colorSchemeDto: ColorSchemeDto) {
        val newColorScheme = ColorScheme(
            colorSchemeDto.id,
            colorSchemeDto.colorHex,
            colorSchemeDto.date,
            colorSchemeDto.sync
        )
        colorSchemeDao.insertColor(newColorScheme)
    }

    override suspend fun deleteColor(id: Long) {
        colorSchemeDao.deleteColor(id)
    }

    override fun getColors(): Flow<List<ColorSchemeDto>> {
        return colorSchemeDao.getColors().map { list ->
            list.map { colorScheme ->
                ColorSchemeDto(
                    id = colorScheme.id,
                    colorHex = colorScheme.colorHex,
                    date = colorScheme.date,
                    sync = colorScheme.sync
                )
            }
        }

    }

    override fun getUnSyncedColors(): Flow<List<ColorSchemeDto>> {
        return colorSchemeDao.getUnSyncedColors().map { list ->
            list.map { colorScheme ->
                ColorSchemeDto(
                    id = colorScheme.id,
                    colorHex = colorScheme.colorHex,
                    date = colorScheme.date,
                    sync = colorScheme.sync
                )
            }
        }
    }

    override suspend fun updateSyncStatus(ids: List<Long>) {
        colorSchemeDao.updateSyncStatus(ids)
    }
}