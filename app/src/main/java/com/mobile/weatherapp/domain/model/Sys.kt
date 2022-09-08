package com.mobile.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sys(
    val country: String,
    val sunrise: Int,
    val sunset: Int,
): Parcelable