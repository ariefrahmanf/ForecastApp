package com.mobile.weatherapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.color.DynamicColors
import com.mobile.weatherapp.di.libModule
import com.mobile.weatherapp.di.reqresModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        DynamicColors.applyToActivitiesIfAvailable(this)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    libModule,
                    reqresModule
                )
            )
        }
    }
}