package com.mobile.weatherapp.data.reqres.local.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Rain

@Entity
data class RainEntity(
    val h: Double?
) {
    fun toRain() = Rain(
        h ?: 0.0
    )
}