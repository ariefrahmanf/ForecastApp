package com.mobile.weatherapp.di

import androidx.room.Room
import com.mobile.weatherapp.data.reqres.local.CurrentWeatherDataStore
import com.mobile.weatherapp.data.reqres.local.CurrentWeatherDatabase
import com.mobile.weatherapp.data.reqres.repository.WeatherDataStore
import com.mobile.weatherapp.data.reqres.web.WeatherApi
import com.mobile.weatherapp.data.reqres.web.WeatherApiClient
import com.mobile.weatherapp.data.utils.converter.MainConverter
import com.mobile.weatherapp.data.utils.converter.WeatherConverter
import com.mobile.weatherapp.data.utils.converter.WindConverter
import com.mobile.weatherapp.domain.repository.WeatherRepository
import com.mobile.weatherapp.domain.usecase.WeatherInteractor
import com.mobile.weatherapp.domain.usecase.WeatherUseCase
import com.mobile.weatherapp.presentation.detailcityweather.DetailCityWeatherViewModel
import com.mobile.weatherapp.presentation.home.HomeViewModel
import com.mobile.weatherapp.utils.ApplicationPreferences
import com.mobile.weatherapp.utils.createCustomService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reqresModule = module {
    val baseUrl = "https://api.openweathermap.org/data/2.5/"
    single { ApplicationPreferences(androidContext()) }

    single {
        createCustomService(
            WeatherApiClient::class.java,
            get(),
            baseUrl
        )
    }

    single {
        WeatherApi(get())
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            CurrentWeatherDatabase::class.java, "weather-db"
        )
            .addTypeConverter(MainConverter())
            .addTypeConverter(WindConverter())
            .addTypeConverter(WeatherConverter())
            .fallbackToDestructiveMigration().build()
    }

    factory { get<CurrentWeatherDatabase>().currentWeatherDao() }
    single{ CurrentWeatherDataStore(currentWeatherDao = get()) }

    single<WeatherRepository> { WeatherDataStore(api = get(), favoriteDao = get()) }
    single<WeatherUseCase> { WeatherInteractor(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailCityWeatherViewModel(get()) }
}