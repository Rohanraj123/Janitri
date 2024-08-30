package com.anim.janitri.di

import android.app.Application
import androidx.room.Room
import com.anim.janitri.data.dao.ColorSchemeDao
import com.anim.janitri.data.datasource.local.JanitriDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): JanitriDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            JanitriDatabase::class.java,
            "janitri_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesColorSchemeDao(janitriDatabase: JanitriDatabase): ColorSchemeDao {
        return janitriDatabase.colorSchemeDao
    }

    @Provides
    @Singleton
    fun providesFireStoreDatabase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}