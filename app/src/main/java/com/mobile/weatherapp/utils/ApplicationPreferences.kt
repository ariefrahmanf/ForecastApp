package com.mobile.weatherapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobile.weatherapp.domain.model.CurrentWeather
import com.mobile.weatherapp.domain.model.ForecastWeather
import com.securepreferences.SecurePreferences
import java.lang.reflect.Type

class ApplicationPreferences(val context: Context) {
    companion object {
        const val PREF_NAME = "Weather Pref"
        const val FAVORITE = "favorite weather"
    }

    @SuppressLint("ObsoleteSdkInt")
    private val preference: SharedPreferences =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val spec = KeyGenParameterSpec.Builder(
                MasterKey.DEFAULT_MASTER_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()
            val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                .setKeyGenParameterSpec(spec)
                .build()
            EncryptedSharedPreferences
                .create(
                    context,
                    PREF_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
        } else {
            SecurePreferences(context, "weather", PREF_NAME)
        }

    private val editor = preference.edit()

    fun addFavorite(data: Pair<CurrentWeather, List<ForecastWeather>>) {
        val favorite = preference.getString(FAVORITE, "")
        val gson = Gson()
        val type: Type =
            object : TypeToken<ArrayList<Pair<CurrentWeather, List<ForecastWeather>>?>?>() {}.type
        var list = gson.fromJson(
            favorite,
            type
        ) as ArrayList<Pair<CurrentWeather, List<ForecastWeather>>?>?
        var tempList: ArrayList<Pair<CurrentWeather, List<ForecastWeather>>?>?

        if (!list.isNullOrEmpty()) {
            tempList = list
            val itr = list.iterator()
            while (itr.hasNext()) {
                val value = itr.next()
                value?.let {
                    if (it.first.name == data.first.name) {
//                        tempList.filter { it?.first?.name != data.first.name }
                    } else {
                        tempList.add(data)
                    }
                }
            }
            val json = gson.toJson(tempList)
            editor.putString(FAVORITE, json)
        } else {
            list = ArrayList()
            list.add(data)
            val json = gson.toJson(list)
            editor.putString(FAVORITE, json)
        }
        editor.apply()
    }

    fun getFavorite(): ArrayList<Pair<CurrentWeather, List<ForecastWeather>>?>? {
        val favorite = preference.getString(FAVORITE, "")
        val gson = Gson()
        val type: Type =
            object : TypeToken<ArrayList<Pair<CurrentWeather, List<ForecastWeather>>?>?>() {}.type
        val list = gson.fromJson(
            favorite,
            type
        ) as ArrayList<Pair<CurrentWeather, List<ForecastWeather>>?>?

        return list
    }
}