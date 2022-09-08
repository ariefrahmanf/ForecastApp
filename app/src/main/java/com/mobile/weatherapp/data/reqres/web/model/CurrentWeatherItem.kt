package com.mobile.weatherapp.data.reqres.web.model

import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.CurrentWeather

data class CurrentWeatherItem(
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sys")
    val sysItem: SysItem,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("clouds")
    val cloudsItem: CloudsItem,
    @SerializedName("wind")
    val wind: WindItem,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("main")
    val main: MainItem,
    @SerializedName("base")
    val base: String,
    @SerializedName("weather")
    val weather: List<WeatherItem>,
    @SerializedName("coord")
    val coord: CoordItem,
) {
    fun toCurrentWeather() = CurrentWeather(
        name,
        id,
        dt,
        wind.toWind(),
        visibility,
        main.toMain(),
        weather.map { it.toWeather() },
    )
}
