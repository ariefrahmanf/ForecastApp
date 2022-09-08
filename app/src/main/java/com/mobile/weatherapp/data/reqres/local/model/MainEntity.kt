package com.mobile.weatherapp.data.reqres.local.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Main

@Entity
data class MainEntity(
    val feelsLike: Double?,
    val grndLevel: Int?,
    val humidity: Int?,
    val pressure: Int?,
    val seaLevel: Int?,
    val temp: Double?,
    val tempKf: Double?,
    val tempMax: Double?,
    val tempMin: Double?
) {
    fun toMain() =
        Main(
            feelsLike ?: 0.0,
            grndLevel ?: 0,
            humidity ?: 0,
            pressure ?: 0,
            seaLevel ?: 0,
            temp ?: 0.0,
            tempKf ?: 0.0,
            tempMax ?: 0.0,
            tempMin ?: 0.0
        )
}