package com.example.sephora.model.database

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class SephoraConverter (){

    val gson =  GsonBuilder().create()

    @TypeConverter
    fun toImageProducts(data: String?): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, listType)
    }


    @TypeConverter
    fun fromImageProducts(data: List<String>): String {
        return gson.toJson(data)
    }
}