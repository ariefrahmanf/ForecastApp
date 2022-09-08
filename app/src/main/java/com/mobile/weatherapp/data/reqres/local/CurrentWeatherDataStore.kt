package com.mobile.weatherapp.data.reqres.local

import com.mobile.weatherapp.data.reqres.local.model.CurrentWeatherEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class CurrentWeatherDataStore (private val currentWeatherDao: CurrentWeatherDao) {

    fun getAllFavorite(): Flowable<List<CurrentWeatherEntity>> {
        return currentWeatherDao.getAllFavorite()
    }

    fun insert(currentWeatherEntity: CurrentWeatherEntity): Completable {
        return currentWeatherDao.insert(currentWeatherEntity)
    }

    fun deleteFromFavorite(city: String): Completable {
        return currentWeatherDao.deleteFromFavorite(city)
    }

    fun getOneFavoriteCity(city: String): Flowable<List<CurrentWeatherEntity>> {
        return currentWeatherDao.getOneFavoriteCity(city)
    }
}