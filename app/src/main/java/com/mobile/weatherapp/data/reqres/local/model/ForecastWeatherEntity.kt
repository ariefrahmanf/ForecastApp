package com.mobile.weatherapp.data.reqres.local.model


import androidx.room.Entity
import com.mobile.weatherapp.domain.model.ForecastWeather
import com.mobile.weatherapp.domain.model.Rain

@Entity
data class ForecastWeatherEntity (
    val cloudsItem: CloudsEntity,
    val dt: Int?,
    val dtTxt: String?,
    val mainItem: MainEntity,
    val pop: Double?,
    val rainItem: RainEntity?,
    val sysItem: SysEntity,
    val visibility: Int?,
    val weatherItem: List<WeatherEntity>,
    val windItem: WindEntity
) {
    fun toForecastWeather() = ForecastWeather(
        cloudsItem.toClouds(),
        dt ?: 0,
        dtTxt ?: "",
        mainItem.toMain(),
        pop ?: 0.0,
        rainItem?.toRain() ?: Rain(0.0),
        sysItem.toSys(),
        visibility ?: 0,
        weatherItem.map { it.toWeather() },
        windItem.toWind()
    )
}