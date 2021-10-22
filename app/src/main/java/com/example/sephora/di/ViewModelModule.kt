package com.example.sephora.di

import com.example.sephora.model.database.SephoraDatabase
import com.example.sephora.repository.MainRepository
import com.example.sephora.repository.NetworkDataSource
import com.example.sephora.repository.WebManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ViewModelModule {

    @Provides
    @Singleton
    fun provideNetworkDataSource(webManager: WebManager): NetworkDataSource {
        return NetworkDataSource(webManager)
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        database: SephoraDatabase,
        networkDataSource: NetworkDataSource, gson: Gson): MainRepository {
        return MainRepository(database, networkDataSource,gson)
    }
}