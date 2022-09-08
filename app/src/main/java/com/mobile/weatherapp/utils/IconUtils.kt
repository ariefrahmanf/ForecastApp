package com.mobile.weatherapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mobile.weatherapp.R

fun setWeatherIcon (iconId: String, context: Context, iv: ImageView) {
    val iconCode = iconId[2].toString()
    if (iconCode == "d") {
        when(iconId) {
            DayIconEnum.CLEAR_SKY.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_sunny)
                    .into(iv)
            }
            DayIconEnum.FEW_CLOUDS.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_cloudy_day)
                    .into(iv)
            }
            DayIconEnum.SCATTERED_CLOUDS.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_scattered_cloud)
                    .into(iv)
            }
            DayIconEnum.BROKEN_CLOUDS.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_broken_cloud)
                    .into(iv)
            }
            DayIconEnum.SHOWER_RAIN.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_day_rain)
                    .into(iv)
            }
            DayIconEnum.RAIN.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_day_rain)
                    .into(iv)
            }
            DayIconEnum.THUNDERSTORM.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_thuderstorm)
                    .into(iv)
            }
            DayIconEnum.SNOW.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_day_snow)
                    .into(iv)
            }
        }
    } else {
        when(iconId) {
            NightIconEnum.CLEAR_SKY.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_night)
                    .into(iv)
            }
            NightIconEnum.FEW_CLOUDS.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_cloudy_night)
                    .into(iv)
            }
            NightIconEnum.SCATTERED_CLOUDS.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_night_scattered_cloud)
                    .into(iv)
            }
            NightIconEnum.BROKEN_CLOUDS.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_night_scattered_cloud)
                    .into(iv)
            }
            NightIconEnum.SHOWER_RAIN.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_night_rain)
                    .into(iv)
            }
            NightIconEnum.RAIN.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_night_rain)
                    .into(iv)
            }
            NightIconEnum.THUNDERSTORM.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_thuderstorm)
                    .into(iv)
            }
            NightIconEnum.SNOW.value -> {
                Glide.with(context)
                    .load(R.drawable.ic_night_snow)
                    .into(iv)
            }
        }
    }
}