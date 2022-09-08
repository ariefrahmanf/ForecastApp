package com.mobile.weatherapp.presentation.detailcityweather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.weatherapp.databinding.TodayForecastItemBinding
import com.mobile.weatherapp.domain.model.ForecastWeather

class ForecastAdapter(private val forecast: List<ForecastWeather>) :
    RecyclerView.Adapter<ForecastAdapter.TodayForecastViewHolder>() {

    class TodayForecastViewHolder(private val binding: TodayForecastItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("SetTextI18n", "CheckResult")
            fun bind(forecastWeather: ForecastWeather) {
                binding.apply {
                    tvHour.text = forecastWeather.dtTxt.subSequence(11,16).toString()
                    tvDegree.text = "${ forecastWeather.mainItem.temp }°C"
                    val iconUrl = "https://openweathermap.org/img/wn/${forecastWeather.weatherItem[0].icon}@2x.png"
                    Glide.with(binding.root)
                        .load(iconUrl)
                        .into(binding.icWeather)
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastAdapter.TodayForecastViewHolder {
        val view = TodayForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodayForecastViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ForecastAdapter.TodayForecastViewHolder,
        position: Int
    ) {
        holder.bind(forecast[position])
    }

    override fun getItemCount(): Int {
        return forecast.size
    }
}