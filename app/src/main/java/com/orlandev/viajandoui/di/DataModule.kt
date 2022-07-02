package com.orlandev.viajandoui.di

import com.orlandev.viajandoui.data.source.remote.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun procideNewsRepository(): NewsRepository {
        return NewsRepository()
    }

}