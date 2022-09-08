package com.mobile.weatherapp.data.reqres.web.model


import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.City

data class CityItem(
    @SerializedName("coord")
    val coordItem: CoordItem,
    @SerializedName("country")
    val country: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("population")
    val population: Int?,
    @SerializedName("sunrise")
    val sunrise: Int?,
    @SerializedName("sunset")
    val sunset: Int?,
    @SerializedName("timezone")
    val timezone: Int?
) {
    fun toCity() = City(
        coordItem.toCoord(),
        country ?: "",
        id ?: 0,
        name ?: "",
        population ?: 0,
        sunrise ?: 0,
        sunset ?: 0,
        timezone ?: 0
    )
}