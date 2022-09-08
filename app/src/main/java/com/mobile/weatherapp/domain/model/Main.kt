package com.mobile.weatherapp.domain.model

import android.os.Parcelable
import com.mobile.weatherapp.data.reqres.local.model.MainEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Main(
    val feelsLike: Double,
    val grndLevel: Int,
    val humidity: Int,
    val pressure: Int,
    val seaLevel: Int,
    val temp: Double,
    val tempKf: Double,
    val tempMax: Double,
    val tempMin: Double
): Parcelable {
    fun toMainEntity() = MainEntity(
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