package com.mobile.weatherapp.data.reqres.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mobile.weatherapp.data.utils.converter.MainConverter
import com.mobile.weatherapp.data.utils.converter.WeatherConverter
import com.mobile.weatherapp.data.utils.converter.WindConverter
import com.mobile.weatherapp.domain.model.CurrentWeather
import com.mobile.weatherapp.domain.model.FavoriteWeather

@Entity(tableName = "current_weather_table")
data class CurrentWeatherEntity(
    val name: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val dt: Int,
    @TypeConverters(WindConverter::class)
    val wind: WindEntity,
    val visibility: Int,
    @TypeConverters(MainConverter::class)
    val main: MainEntity,
    @TypeConverters(WeatherConverter::class)
    val weather: List<WeatherEntity>,
) {
    fun toFavoriteCityWeather() = FavoriteWeather(
        name,
        id,
        dt,
        wind.toWind(),
        visibility,
        main.toMain(),
        weather.map { it.toWeather() },
    )

}
