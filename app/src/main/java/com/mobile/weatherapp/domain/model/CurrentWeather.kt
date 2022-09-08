package com.mobile.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeather(
    val name: String,
    val id: Int,
    val dt: Int,
    val wind: Wind,
    val visibility: Int,
    val main: Main,
    val weather: List<Weather>,
): Parcelable
