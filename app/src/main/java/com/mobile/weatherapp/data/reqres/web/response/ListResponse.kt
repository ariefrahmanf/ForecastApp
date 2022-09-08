package com.mobile.weatherapp.data.reqres.web.response

import com.google.gson.annotations.SerializedName

data class ListResponse<T>(
    @SerializedName("list")
    val list: List<T>
)
