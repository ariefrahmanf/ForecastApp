package com.mobile.weatherapp.domain.usecase

import com.mobile.weatherapp.data.reqres.local.model.CurrentWeatherEntity
import com.mobile.weatherapp.domain.model.CurrentWeather
import com.mobile.weatherapp.domain.model.FavoriteWeather
import com.mobile.weatherapp.domain.model.ForecastWeather
import com.mobile.weatherapp.domain.repository.WeatherRepository
import com.mobile.weatherapp.utils.Resource
import io.reactivex.Flowable

class WeatherInteractor(private val repo: WeatherRepository): WeatherUseCase {
    override fun getWeatherBasedOnCity(city: String): Flowable<Resource<CurrentWeather>>? {
        return repo.getWeatherBasedOnCity(city)
    }

    override fun getForecastWeather(city: String): Flowable<Resource<List<ForecastWeather>>>? {
        return  repo.getForecastWeather(city)
    }

    override fun getAllFavoriteCity(): Flowable<List<FavoriteWeather>> {
        return repo.getAllFavoriteCity()
    }

    override fun addFavoriteCity(currentWeatherEntity: CurrentWeatherEntity) {
        return repo.addFavoriteCity(currentWeatherEntity)
    }

    override fun deleteFavoriteCity(city: String) {
        repo.deleteFavoriteCity(city)
    }

    override fun getOneFavoriteCity(city: String): Flowable<List<FavoriteWeather>> {
        return repo.getOneFavoriteCity(city)
    }
}