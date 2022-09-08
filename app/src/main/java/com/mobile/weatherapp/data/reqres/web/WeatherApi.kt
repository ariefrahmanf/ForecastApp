package com.mobile.weatherapp.data.reqres.web

import android.annotation.SuppressLint
import android.util.Log
import com.mobile.weatherapp.data.reqres.web.model.CurrentWeatherItem
import com.mobile.weatherapp.data.reqres.web.model.ForecastWeatherItem
import com.mobile.weatherapp.data.utils.ApiResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class WeatherApi(private val web: WeatherApiClient) {

    @SuppressLint("CheckResult")
    fun getWeatherBasedOnCity(cityName: String): Flowable<ApiResponse<CurrentWeatherItem>> {
        val resultData = PublishSubject.create<ApiResponse<CurrentWeatherItem>>()

        val client = web.getWeatherBasedOnCity(cityName)

        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                resultData.onNext(if (response.weather.isNotEmpty()) ApiResponse.Success(response) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getForecastWeather(city: String): Flowable<ApiResponse<List<ForecastWeatherItem>>> {
        val resultData = PublishSubject.create<ApiResponse<List<ForecastWeatherItem>>>()

        val client = web.getForecastWeather(city)

        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                resultData.onNext(if (response.list.isNotEmpty()) ApiResponse.Success(response.list) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}