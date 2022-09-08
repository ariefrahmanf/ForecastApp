package com.mobile.weatherapp.data.reqres.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobile.weatherapp.data.reqres.local.model.CurrentWeatherEntity
import com.mobile.weatherapp.data.utils.converter.MainConverter
import com.mobile.weatherapp.data.utils.converter.WeatherConverter
import com.mobile.weatherapp.data.utils.converter.WindConverter

@Database(entities = [CurrentWeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(MainConverter::class, WeatherConverter::class, WindConverter::class)
abstract class CurrentWeatherDatabase: RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
}