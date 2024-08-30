package com.anim.janitri.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anim.janitri.data.datasource.local.ColorScheme
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorSchemeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertColor(colorScheme: ColorScheme)

    @Query("DELETE FROM color_scheme WHERE id= :id")
    suspend fun deleteColor(id: Long)

    @Query("SELECT * FROM color_scheme")
    fun getColors(): Flow<List<ColorScheme>>

    @Query("SELECT * FROM color_scheme WHERE sync=0")
    fun getUnSyncedColors(): Flow<List<ColorScheme>>

    @Query("UPDATE color_scheme SET sync=1 WHERE id IN (:ids)")
    suspend fun updateSyncStatus(ids: List<Long>)
}