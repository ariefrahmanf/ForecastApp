package com.mobile.weatherapp.domain.model

import android.os.Parcelable
import com.mobile.weatherapp.data.reqres.local.model.WeatherEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
) : Parcelable {
    fun toWeatherEntity() = WeatherEntity(
        description,
        icon,
        id,
        main
    )
}