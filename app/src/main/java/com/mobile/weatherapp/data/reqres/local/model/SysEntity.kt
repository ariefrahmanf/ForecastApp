package com.mobile.weatherapp.data.reqres.local.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Sys

@Entity
data class SysEntity(
    val country: String?,
    val sunrise: Int?,
    val sunset: Int?,
) {
    fun toSys() = Sys(
        country ?: "",
        sunrise ?: 0,
        sunset ?: 0
    )
}