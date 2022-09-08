package com.mobile.weatherapp.data.reqres.local

import androidx.room.*
import com.mobile.weatherapp.data.reqres.local.model.CurrentWeatherEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface CurrentWeatherDao {
    @Query("SELECT * FROM current_weather_table")
    fun getAllFavorite(): Flowable<List<CurrentWeatherEntity>>

    @Query("SELECT * FROM current_weather_table WHERE name = :city")
    fun getOneFavoriteCity(city: String): Flowable<List<CurrentWeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentWeatherEntity: CurrentWeatherEntity): Completable

    @Query("DELETE FROM current_weather_table WHERE name = :city")
    fun deleteFromFavorite(city: String): Completable
}