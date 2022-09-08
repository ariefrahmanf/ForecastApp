package com.mobile.weatherapp.data.reqres.local.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Coord

data class CoordEntity(
    val lat: Double?,
    val lon: Double?
) {
    fun toCoord() = Coord(
        lat ?: 0.0,
        lon ?: 0.0
    )
}