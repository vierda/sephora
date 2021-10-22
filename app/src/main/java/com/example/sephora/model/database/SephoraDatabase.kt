package com.example.sephora.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sephora.model.Product
import javax.inject.Named

@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(SephoraConverter::class)
@Named("sephora_db")
abstract class SephoraDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}