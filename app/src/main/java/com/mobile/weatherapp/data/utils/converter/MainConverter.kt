package com.mobile.weatherapp.data.utils.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobile.weatherapp.data.reqres.local.model.MainEntity

@ProvidedTypeConverter

class MainConverter {
    val gson = Gson()

    @TypeConverter
    fun mainToString(main: MainEntity): String {
        return gson.toJson(main)
    }

    @TypeConverter
    fun stringToMain(mainString: String): MainEntity {
        val type = object : TypeToken<MainEntity>() {}.type
        return gson.fromJson(mainString, type)
    }
}