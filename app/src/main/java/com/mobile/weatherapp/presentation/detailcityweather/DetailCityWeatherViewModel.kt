package com.mobile.weatherapp.presentation.detailcityweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.weatherapp.data.reqres.local.model.CurrentWeatherEntity
import com.mobile.weatherapp.domain.model.CurrentWeather
import com.mobile.weatherapp.domain.model.FavoriteWeather
import com.mobile.weatherapp.domain.model.ForecastWeather
import com.mobile.weatherapp.domain.usecase.WeatherUseCase
import com.mobile.weatherapp.utils.dateMillisecondFormatter
import io.reactivex.disposables.CompositeDisposable

class DetailCityWeatherViewModel(private val useCase: WeatherUseCase) : ViewModel() {
    var bookmark = false
    var forecastWeather = emptyList<ForecastWeather>()

    fun filterTodayForecast(todayDate: Long) = forecastWeather.filter {
        it.dtTxt.subSequence(0, 10) == dateMillisecondFormatter(todayDate)
    }

    fun filterNextDayForecast(todayDate: Long) = forecastWeather.filter {
        it.dtTxt.subSequence(0, 10) != dateMillisecondFormatter(todayDate)
    }

    fun getForecastWeather(city: String) =
        LiveDataReactiveStreams.fromPublisher(useCase.getForecastWeather(city)!!)

    fun addToFavorite(currentWeather: CurrentWeather) {
        val currentWeatherEntity = CurrentWeatherEntity(
            name = currentWeather.name,
            id = currentWeather.id,
            dt = currentWeather.dt,
            wind = currentWeather.wind.toWindEntity(),
            visibility = currentWeather.visibility,
            main = currentWeather.main.toMainEntity(),
            weather = currentWeather.weather.map { it.toWeatherEntity() }
        )
        useCase.addFavoriteCity(currentWeatherEntity)
    }

    fun deleteFromFavorite(city: String) {

        useCase.deleteFavoriteCity(city)
    }

    fun getOneFavoriteCity(cityName: String): LiveData<List<FavoriteWeather>> {
        val city = useCase.getOneFavoriteCity(cityName)
        return LiveDataReactiveStreams.fromPublisher(city)
    }
}