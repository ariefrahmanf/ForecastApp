package com.mobile.weatherapp.data.reqres.web.model


import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Rain

data class RainItem(
    @SerializedName("3h")
    val h: Double?
) {
    fun toRain() = Rain(
        h ?: 0.0
    )
}