package com.nikosgig.weatherforecastmvvm.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val type = object: TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val type = object: TypeToken<List<String>>() {}.type
        return Gson().toJson(list, type)
    }
}