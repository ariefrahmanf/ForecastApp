package com.mobile.weatherapp.data.reqres.web.model


import com.google.gson.annotations.SerializedName
import com.mobile.weatherapp.domain.model.Main

data class MainItem(
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("grnd_level")
    val grndLevel: Int?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("sea_level")
    val seaLevel: Int?,
    @SerializedName("temp")
    val temp: Double?,
    @SerializedName("temp_kf")
    val tempKf: Double?,
    @SerializedName("temp_max")
    val tempMax: Double?,
    @SerializedName("temp_min")
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