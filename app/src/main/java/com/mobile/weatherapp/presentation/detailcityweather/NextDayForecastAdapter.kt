package com.mobile.weatherapp.presentation.detailcityweather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.weatherapp.databinding.NextDayForecastItemBinding
import com.mobile.weatherapp.databinding.TodayForecastItemBinding
import com.mobile.weatherapp.domain.model.ForecastWeather
import com.mobile.weatherapp.utils.getDayName
import com.mobile.weatherapp.utils.getDayNameFromDateText

class NextDayForecastAdapter(private val forecast: List<ForecastWeather>) :
    RecyclerView.Adapter<NextDayForecastAdapter.TodayForecastViewHolder>() {

    class TodayForecastViewHolder(private val binding: NextDayForecastItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("SetTextI18n", "CheckResult")
            fun bind(forecastWeather: ForecastWeather) {
                binding.apply {
                    tvDay.text = getDayNameFromDateText(forecastWeather.dtTxt)
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
    ): NextDayForecastAdapter.TodayForecastViewHolder {
        val view = NextDayForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodayForecastViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NextDayForecastAdapter.TodayForecastViewHolder,
        position: Int
    ) {
        holder.bind(forecast[position])
    }

    override fun getItemCount(): Int {
        return forecast.size
    }
}