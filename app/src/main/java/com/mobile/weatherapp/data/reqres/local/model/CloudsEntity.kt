package com.mobile.weatherapp.data.reqres.local.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Clouds

data class CloudsEntity(
    val all: Int?
) {
    fun toClouds() = Clouds(
        all ?: 0
    )
}