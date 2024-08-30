package com.anim.janitri.di

import android.app.Application
import androidx.room.Room
import com.anim.janitri.data.dao.ColorSchemeDao
import com.anim.janitri.data.datasource.local.JanitriDatabase
import com.anim.janitri.data.repositories.ColorSchemeRepoImpl
import com.anim.janitri.domain.repositories.ColorSchemeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providesColorSchemeRepo(colorSchemeDao: ColorSchemeDao): ColorSchemeRepo {
        return ColorSchemeRepoImpl(colorSchemeDao)
    }
}