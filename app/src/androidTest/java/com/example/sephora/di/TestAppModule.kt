package com.example.sephora.di

import android.content.Context
import androidx.room.Room
import com.example.sephora.model.database.SephoraDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    @Named("test_db")
    fun provideInMemoryDb (@ApplicationContext context:Context) =
        Room.inMemoryDatabaseBuilder(context, SephoraDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}