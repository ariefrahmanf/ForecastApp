package com.mobile.weatherapp.data.reqres.local.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Wind

data class WindEntity(
    val deg: Int?,
    val gust: Double?,
    val speed: Double?
) {
    fun toWind() = Wind(
        deg ?: 0,
        gust ?: 0.0,
        speed ?: 0.0
    )
}