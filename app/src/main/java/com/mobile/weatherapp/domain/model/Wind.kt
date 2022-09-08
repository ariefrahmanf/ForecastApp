package com.mobile.weatherapp.domain.model

import android.os.Parcelable
import com.mobile.weatherapp.data.reqres.local.model.WindEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
): Parcelable {
    fun toWindEntity() = WindEntity(
        deg,
        gust,
        speed
    )
}