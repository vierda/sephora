package com.example.sephora.di

import android.content.Context
import androidx.room.Room
import com.example.sephora.model.database.SephoraDatabase
import com.example.sephora.repository.WebManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideActivityContext(@ActivityContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideWebManager(): WebManager = WebManager.create()


    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): SephoraDatabase {
        return Room.databaseBuilder(
            context, SephoraDatabase::class.java, "SephoraDb")
            .fallbackToDestructiveMigration()
            .build()
    }
}