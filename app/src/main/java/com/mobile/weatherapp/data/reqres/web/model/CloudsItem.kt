package com.mobile.weatherapp.data.reqres.web.model


import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Clouds

data class CloudsItem(
    @SerializedName("all")
    val all: Int?
) {
    fun toClouds() = Clouds(
        all ?: 0
    )
}