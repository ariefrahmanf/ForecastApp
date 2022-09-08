package com.mobile.weatherapp.data.utils.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobile.weatherapp.data.reqres.local.model.MainEntity
import com.mobile.weatherapp.data.reqres.local.model.WeatherEntity

@ProvidedTypeConverter
class WeatherConverter {
    val gson = Gson()

    @TypeConverter
    fun weatherToString(main: List<WeatherEntity>): String {
        return gson.toJson(main)
    }

    @TypeConverter
    fun stringToWeather(mainString: String): List<WeatherEntity> {
        val type = object : TypeToken<List<WeatherEntity>>() {}.type
        return gson.fromJson(mainString, type)
    }
}