package com.mobile.weatherapp.domain.usecase

import com.mobile.weatherapp.data.reqres.local.model.CurrentWeatherEntity
import com.mobile.weatherapp.domain.model.CurrentWeather
import com.mobile.weatherapp.domain.model.FavoriteWeather
import com.mobile.weatherapp.domain.model.ForecastWeather
import com.mobile.weatherapp.utils.Resource
import io.reactivex.Flowable

interface WeatherUseCase {
    fun getWeatherBasedOnCity(city: String): Flowable<Resource<CurrentWeather>>?
    fun getForecastWeather(city: String): Flowable<Resource<List<ForecastWeather>>>?
    fun getAllFavoriteCity(): Flowable<List<FavoriteWeather>>
    fun addFavoriteCity(currentWeatherEntity: CurrentWeatherEntity)
    fun deleteFavoriteCity(city: String)
    fun getOneFavoriteCity(city: String): Flowable<List<FavoriteWeather>>
}