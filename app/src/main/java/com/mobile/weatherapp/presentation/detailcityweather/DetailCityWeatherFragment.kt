package com.mobile.weatherapp.presentation.detailcityweather

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.transition.MaterialSharedAxis
import com.mobile.weatherapp.R
import com.mobile.weatherapp.databinding.FragmentDetailCityWeatherBinding
import com.mobile.weatherapp.domain.model.CurrentWeather
import com.mobile.weatherapp.presentation.home.HomeViewModel
import com.mobile.weatherapp.utils.ApplicationPreferences
import com.mobile.weatherapp.utils.Resource
import com.mobile.weatherapp.utils.dateSecondFormatter
import com.mobile.weatherapp.utils.setWeatherIcon
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailCityWeatherFragment : Fragment() {
    private var _binding: FragmentDetailCityWeatherBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModel<DetailCityWeatherViewModel>()
    private val vmHome by sharedViewModel<HomeViewModel>()
    private val args by navArgs<DetailCityWeatherFragmentArgs>()
    private val pref by inject<ApplicationPreferences>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration =
                requireContext().resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration =
                requireContext().resources.getInteger(R.integer.motion_duration_large).toLong()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailCityWeatherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BottomSheetBehavior.from(binding.bottomSheetForecast).apply {
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        initUI(args.currentWeather)
        getForecastWeather()
        getBookmarkedCity()
        binding.btnBookmark.setOnClickListener {
            if (vm.bookmark) {
                vm.deleteFromFavorite(args.currentWeather.name)
                getBookmarkedCity()
                binding.btnBookmark.isChecked = false
            } else {
                vm.addToFavorite(args.currentWeather)
                getBookmarkedCity()
                binding.btnBookmark.isChecked = true
            }
        }

        refreshCurrentWeather()
    }

    private fun refreshCurrentWeather() {
        binding.refreshLayout.setOnRefreshListener {
            vmHome.getWeather(args.currentWeather.name)
            vmHome.listWeather.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Empty -> {
                        binding.refreshLayout.isRefreshing = false

                    }
                    is Resource.Success -> {
                        if (vm.bookmark) {
                            it.data?.apply {
                                vm.addToFavorite(this)
                                initUI(this)
                            }
                        }
                        getForecastWeather()
                        binding.refreshLayout.isRefreshing = false
                    }
                    is Resource.Error -> {
                        binding.refreshLayout.isRefreshing = false

                    }
                }
            }
        }
    }

    private fun getBookmarkedCity() {
        vm.getOneFavoriteCity(args.currentWeather.name).observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                binding.btnBookmark.isChecked = true
                vm.bookmark = true
            } else {
                binding.btnBookmark.isChecked = false
                vm.bookmark = false
            }
        }
    }

    private fun getForecastWeather() {
        val forecast = vm.getForecastWeather(args.currentWeather.name)
        forecast.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Empty -> {

                }
                is Resource.Success -> {
                    it.data?.apply {
                        vm.forecastWeather = this
                        val todayForecast = vm.filterTodayForecast(Date().time)
                        val nextDayForecast = vm.filterNextDayForecast(Date().time)

                        val todayForecastAdapter = ForecastAdapter(todayForecast)
                        val nextDayForecastAdapter = NextDayForecastAdapter(nextDayForecast)

                        binding.rvForecastWeatherToday.apply {
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            adapter = todayForecastAdapter
                        }

                        binding.rvForecastWeatherNextDay.apply {
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                            adapter = nextDayForecastAdapter
                        }
                    }
                }
                is Resource.Error -> {

                }
            }
        }
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    private fun initUI(currentWeather: CurrentWeather) {
        binding.apply {
            currentWeather.apply {
                setWeatherIcon(weather[0].icon, requireContext(), binding.ivWeather)
                tvDate.text = dateSecondFormatter(dt.toLong())
                tvWeather.text = weather[0].description.capitalize()
                tvDegree.text = "${main.feelsLike}°C"
                tvCity.text = name

                cvHumidity.let {
                    it.tvItemName.text = "Humidity"
                    it.tvItemValue.text = "${main.humidity}%"
                    Glide.with(requireContext())
                        .load(R.drawable.ic_hummidity)
                        .into(it.icItem)
                }
                cvPressure.let {
                    it.tvItemName.text = "Pressure"
                    it.tvItemValue.text = "${main.pressure}hPa"
                    Glide.with(requireContext())
                        .load(R.drawable.ic_pressure)
                        .into(it.icItem)
                }
                cvTemperature.let {
                    it.tvItemName.text = "Temperature"
                    it.tvItemValue.text = "${main.feelsLike}°C"
                    Glide.with(requireContext())
                        .load(R.drawable.ic_thermometer)
                        .into(it.icItem)
                }
                cvWind.let {
                    it.tvItemName.text = "Wind"
                    it.tvItemValue.text = "${wind.speed}m/h"
                    Glide.with(requireContext())
                        .load(R.drawable.ic_wind)
                        .into(it.icItem)
                }
            }
        }
    }
}