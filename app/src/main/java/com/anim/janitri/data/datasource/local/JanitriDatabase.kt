package com.anim.janitri.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anim.janitri.data.dao.ColorSchemeDao

@Database(entities = [ColorScheme::class], version = 1, exportSchema = false)
abstract class JanitriDatabase: RoomDatabase() {
    abstract val colorSchemeDao: ColorSchemeDao
}