package com.mobile.weatherapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.weatherapp.data.reqres.local.model.CurrentWeatherEntity
import com.mobile.weatherapp.domain.model.CurrentWeather
import com.mobile.weatherapp.domain.model.FavoriteWeather
import com.mobile.weatherapp.domain.usecase.WeatherUseCase
import com.mobile.weatherapp.utils.Resource

class HomeViewModel(val useCase: WeatherUseCase): ViewModel() {
    private var _listWeather: LiveData<Resource<CurrentWeather>> = MutableLiveData()
    val listWeather: LiveData<Resource<CurrentWeather>> get() = _listWeather

    fun getWeather(city: String) {
        _listWeather = LiveDataReactiveStreams.fromPublisher(useCase.getWeatherBasedOnCity(city)!!)
    }

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

    fun getAllBookmarkedCity(): LiveData<List<FavoriteWeather>> {
        val city = useCase.getAllFavoriteCity()
        return LiveDataReactiveStreams.fromPublisher(city)
    }
}