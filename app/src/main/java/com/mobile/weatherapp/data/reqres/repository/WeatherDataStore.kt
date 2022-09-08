package com.mobile.weatherapp.data.reqres.repository

import android.annotation.SuppressLint
import com.mobile.weatherapp.data.reqres.local.CurrentWeatherDataStore
import com.mobile.weatherapp.data.reqres.local.model.CurrentWeatherEntity
import com.mobile.weatherapp.data.reqres.web.WeatherApi
import com.mobile.weatherapp.data.utils.ApiResponse
import com.mobile.weatherapp.domain.model.CurrentWeather
import com.mobile.weatherapp.domain.model.FavoriteWeather
import com.mobile.weatherapp.domain.model.ForecastWeather
import com.mobile.weatherapp.domain.repository.WeatherRepository
import com.mobile.weatherapp.utils.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class WeatherDataStore(
    private val api: WeatherApi,
    private val favoriteDao: CurrentWeatherDataStore
) :
    WeatherRepository {
    override fun getWeatherBasedOnCity(cityName: String): Flowable<Resource<CurrentWeather>>? {
        val result = PublishSubject.create<Resource<CurrentWeather>>()
        val mCompositeDisposable = CompositeDisposable()

        val apiResponse = api.getWeatherBasedOnCity(cityName)
        result.onNext(Resource.Loading(null))
        val response = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                mCompositeDisposable.dispose()
            }
            .subscribe { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        result.onNext(Resource.Success(response.data.toCurrentWeather()))
                    }
                    is ApiResponse.Empty -> {
                        result.onNext(Resource.Empty())
                    }
                    is ApiResponse.Error -> {
                        result.onNext(Resource.Error(response.errorMessage, null))
                    }
                }
            }
        mCompositeDisposable.add(response)

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getForecastWeather(city: String): Flowable<Resource<List<ForecastWeather>>>? {
        val result = PublishSubject.create<Resource<List<ForecastWeather>>>()
        val mCompositeDisposable = CompositeDisposable()

        val apiResponse = api.getForecastWeather(city)
        result.onNext(Resource.Loading(null))
        val response = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                mCompositeDisposable.dispose()
            }
            .subscribe { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        result.onNext(Resource.Success(response.data.map { it.toForecastWeather() }))
                    }
                    is ApiResponse.Empty -> {
                        result.onNext(Resource.Empty())
                    }
                    is ApiResponse.Error -> {
                        result.onNext(Resource.Error(response.errorMessage, null))
                    }
                }
            }
        mCompositeDisposable.add(response)

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun getAllFavoriteCity(): Flowable<List<FavoriteWeather>> {
        val mCompositeDisposable = CompositeDisposable()
        val result = PublishSubject.create<List<FavoriteWeather>>()
        val dbSource = favoriteDao.getAllFavorite()
        val db = dbSource.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                mCompositeDisposable.dispose()
            }
            .subscribe {
                dbSource.unsubscribeOn(Schedulers.io())
                result.onNext(it.map { it.toFavoriteCityWeather() })
            }
        mCompositeDisposable.add(db)
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun addFavoriteCity(currentWeatherEntity: CurrentWeatherEntity) {
        val mCompositeDisposable = CompositeDisposable()
        val db = favoriteDao.insert(currentWeatherEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                mCompositeDisposable.dispose()
            }
            .subscribe()
        mCompositeDisposable.add(db)
    }

    @SuppressLint("CheckResult")
    override fun deleteFavoriteCity(city: String) {
        val mCompositeDisposable = CompositeDisposable()
        val db = favoriteDao.deleteFromFavorite(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                mCompositeDisposable.dispose()
            }
            .subscribe()
        mCompositeDisposable.add(db)
    }

    @SuppressLint("CheckResult")
    override fun getOneFavoriteCity(city: String): Flowable<List<FavoriteWeather>> {
        val mCompositeDisposable = CompositeDisposable()
        val result = PublishSubject.create<List<FavoriteWeather>>()
        val dbSource = favoriteDao.getOneFavoriteCity(city)
        val db = dbSource.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                mCompositeDisposable.dispose()
            }
            .subscribe {
                dbSource.unsubscribeOn(Schedulers.io())
                result.onNext(it.map { it.toFavoriteCityWeather() })
            }
        mCompositeDisposable.add(db)
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}