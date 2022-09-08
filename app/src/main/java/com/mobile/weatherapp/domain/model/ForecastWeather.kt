package com.mobile.weatherapp.domain.model

data class ForecastWeather (
    val cloudsItem: Clouds,
    val dt: Int,
    val dtTxt: String,
    val mainItem: Main,
    val pop: Double,
    val rain: Rain,
    val sysItem: Sys,
    val visibility: Int,
    val weatherItem: List<Weather>,
    val windItem: Wind
)