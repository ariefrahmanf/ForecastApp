package com.mobile.weatherapp.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialSharedAxis
import com.mobile.weatherapp.R
import com.mobile.weatherapp.databinding.FragmentHomeBinding
import com.mobile.weatherapp.domain.model.CurrentWeather
import com.mobile.weatherapp.domain.model.FavoriteWeather
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val vm: HomeViewModel by sharedViewModel()
    private val nav by lazy { activity?.findNavController(R.id.main_content) }
    private lateinit var bookmarkedCityAdapter: BookmarkedCityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etSearch.keyListener = null

        binding.etSearch.setOnClickListener {
            navigateToSearchFragment()
        }

        getBookmarkedCity()
    }

    private fun getBookmarkedCity() {
        vm.getAllBookmarkedCity().observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                bookmarkedCityAdapter = BookmarkedCityAdapter(it) {
                    navigateToDetailCityWeather(it)
                }
                binding.rvFavorites.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
                    adapter = bookmarkedCityAdapter
                }
            }
        }
    }

    private fun navigateToDetailCityWeather(bookmarkedCity: FavoriteWeather) {
        val currentWeather = CurrentWeather(
            bookmarkedCity.name,
            bookmarkedCity.id,
            bookmarkedCity.dt,
            bookmarkedCity.wind,
            bookmarkedCity.visibility,
            bookmarkedCity.main,
            bookmarkedCity.weather
        )
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = requireContext().resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = requireContext().resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        nav?.navigate(HomeFragmentDirections.actionHomeFragmentToDetailCityWeatherFragment(currentWeather))
    }

    private fun navigateToSearchFragment() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = requireContext().resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = requireContext().resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        nav?.navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
    }
}