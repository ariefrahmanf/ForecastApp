package com.mobile.weatherapp.data.reqres.local.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Weather

@Entity
data class WeatherEntity(
    val description: String?,
    val icon: String?,
    val id: Int?,
    val main: String?
) {
    fun toWeather() =
        Weather(
            description ?: "",
            icon ?: "",
            id ?: 0,
            main ?: ""
        )
}