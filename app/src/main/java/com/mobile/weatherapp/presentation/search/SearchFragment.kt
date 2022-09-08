package com.mobile.weatherapp.presentation.search

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import com.mobile.weatherapp.R
import com.mobile.weatherapp.databinding.FragmentSearchBinding
import com.mobile.weatherapp.domain.model.CurrentWeather
import com.mobile.weatherapp.presentation.home.HomeViewModel
import com.mobile.weatherapp.utils.DayIconEnum
import com.mobile.weatherapp.utils.Resource
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val vm: HomeViewModel by sharedViewModel()
    private val nav by lazy { activity?.findNavController(R.id.main_content) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = requireContext().resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = requireContext().resources.getInteger(R.integer.motion_duration_large).toLong()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        binding.etSearch.requestFocus()
        showSoftKeyboard()
        backPressed()
        searchCity()
        observedSearchResult()
    }

    @SuppressLint("SetTextI18n")
    private fun observedSearchResult() {
        vm.listWeather.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.cvCityResult.visibility = View.VISIBLE
                    it.data?.let { result ->
                        with(binding) {
                            tvCity.text = result.name
                            tvWeather.text = result.weather[0].main
                            tvDegree.text = "${result.main.feelsLike}°C"
                            tvWindValue.text = "${result.wind.speed}m/h"
                            tvVisibilityValue.text = "${result.visibility/1000}km"
                            tvHumidityValue.text = "${result.main.humidity}%"
                            tvPressureValue.text = "${result.main.pressure/1000}hPa"
                        }
                        binding.cvCityResult.setOnClickListener {
                            nav?.navigate(SearchFragmentDirections.actionSearchFragmentToDetailCityWeatherFragment(result))
                        }
                    }
                }
                is Resource.Empty -> {
                    binding.cvCityResult.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.cvCityResult.visibility = View.GONE
                }
            }
        }
    }

    private fun navigateToDetailCityWeather(currentWeather: CurrentWeather) {
        nav?.navigate(SearchFragmentDirections.actionSearchFragmentToDetailCityWeatherFragment(currentWeather))
    }

    private fun backPressed() {
        binding.btnBack.setOnClickListener { nav?.navigateUp() }
    }

    private fun showSoftKeyboard() {
        Handler(Looper.getMainLooper()).postDelayed({
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.etSearch, InputMethodManager.SHOW_IMPLICIT)
        }, 500)
    }

    private fun searchCity() {
        binding.etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    vm.getWeather(v?.text.toString())
                    observedSearchResult()
                    return true
                }
                return false
            }
        })
    }
}