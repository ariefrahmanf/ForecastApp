package com.mobile.weatherapp.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun <T> createCustomService(
    serviceClass: Class<T>,
    okHttpClient: OkHttpClient,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(serviceClass)
}