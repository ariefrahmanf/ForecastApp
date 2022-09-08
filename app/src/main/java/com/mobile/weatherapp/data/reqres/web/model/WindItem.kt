package com.mobile.weatherapp.data.reqres.web.model


import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Wind

data class WindItem(
    @SerializedName("deg")
    val deg: Int?,
    @SerializedName("gust")
    val gust: Double?,
    @SerializedName("speed")
    val speed: Double?
) {
    fun toWind() = Wind(
        deg ?: 0,
        gust ?: 0.0,
        speed ?: 0.0
    )
}