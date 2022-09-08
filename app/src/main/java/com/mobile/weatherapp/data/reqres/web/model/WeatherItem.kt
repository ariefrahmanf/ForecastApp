package com.mobile.weatherapp.data.reqres.web.model

import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Weather

data class WeatherItem(
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
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