package com.mobile.weatherapp.data.reqres.web.model


import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Sys

data class SysItem(
    @SerializedName("country")
    val country: String?,
    @SerializedName("sunrise")
    val sunrise: Int?,
    @SerializedName("sunset")
    val sunset: Int?,
) {
    fun toSys() = Sys(
        country ?: "",
        sunrise ?: 0,
        sunset ?: 0
    )
}