package com.mobile.weatherapp.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.weatherapp.databinding.BookmarkedCityItemBinding
import com.mobile.weatherapp.domain.model.FavoriteWeather
import com.mobile.weatherapp.utils.setWeatherIcon

class BookmarkedCityAdapter(
    private val list: List<FavoriteWeather>,
    private val onClick: (FavoriteWeather) -> Unit?
) :
    RecyclerView.Adapter<BookmarkedCityAdapter.BookmarkedCityViewHolder>() {

    inner class BookmarkedCityViewHolder(val binding: BookmarkedCityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(weather: FavoriteWeather) {
            binding.apply {
                tvDegree.text = "${weather.main.feelsLike}°C"
                tvCityName.text = weather.name
                tvHumidity.text = "${weather.main.humidity}%"
                tvWind.text = "${weather.wind.speed}m/h"
                setWeatherIcon(weather.weather[0].icon, root.context, icWeather)
                container.setOnClickListener {
                    onClick.invoke(weather)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkedCityAdapter.BookmarkedCityViewHolder {
        val view =
            BookmarkedCityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkedCityViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: BookmarkedCityAdapter.BookmarkedCityViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}