package com.mobile.weatherapp.data.reqres.local.model


import androidx.room.Entity
import com.mobile.weatherapp.domain.model.City

@Entity
data class CityEntity(
    val coordItem: CoordEntity,
    val country: String?,
    val id: Int?,
    val name: String?,
    val population: Int?,
    val sunrise: Int?,
    val sunset: Int?,
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