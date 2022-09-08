package com.mobile.weatherapp.data.reqres.web.model


import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.ForecastWeather
import com.mobile.weatherapp.domain.model.Rain

data class ForecastWeatherItem (
    @SerializedName("clouds")
    val cloudsItem: CloudsItem,
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("dt_txt")
    val dtTxt: String?,
    @SerializedName("main")
    val mainItem: MainItem,
    @SerializedName("pop")
    val pop: Double?,
    @SerializedName("rain")
    val rainItem: RainItem?,
    @SerializedName("sys")
    val sysItem: SysItem,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("weather")
    val weatherItem: List<WeatherItem>,
    @SerializedName("wind")
    val windItem: WindItem
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