package com.mobile.weatherapp.data.reqres.web.model


import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Coord

data class CoordItem(
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?
) {
    fun toCoord() = Coord(
        lat ?: 0.0,
        lon ?: 0.0
    )
}