package com.mobile.weatherapp.data.utils.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobile.weatherapp.data.reqres.local.model.MainEntity
import com.mobile.weatherapp.data.reqres.local.model.WindEntity

@ProvidedTypeConverter
class WindConverter {
    val gson = Gson()

    @TypeConverter
    fun windToString(main: WindEntity): String {
        return gson.toJson(main)
    }

    @TypeConverter
    fun stringToWind(windString: String): WindEntity {
        val type = object : TypeToken<WindEntity>() {}.type
        return gson.fromJson(windString, type)
    }
}