package com.mobile.weatherapp.data.reqres.web

import com.mobile.weatherapp.data.reqres.web.model.CurrentWeatherItem
import com.mobile.weatherapp.data.reqres.web.model.ForecastWeatherItem
import com.mobile.weatherapp.data.reqres.web.response.ListResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {

    @GET("weather?appid=dabd552c73d20c4782da17cd089dd68f&units=metric")
    fun getWeatherBasedOnCity(
        @Query("q") cityName: String
    ): Flowable<CurrentWeatherItem>

    @GET("forecast?appid=d80d671a0a97917cb818dfbaf07b6b08&units=metric")
    fun getForecastWeather(@Query("q") city: String): Flowable<ListResponse<ForecastWeatherItem>>
}