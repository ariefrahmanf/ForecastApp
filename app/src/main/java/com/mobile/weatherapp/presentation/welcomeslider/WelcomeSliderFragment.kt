package com.mobile.weatherapp.presentation.welcomeslider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mobile.weatherapp.R
import com.mobile.weatherapp.databinding.FragmentWelcomeSliderBinding
import com.mobile.weatherapp.utils.SliderItem

class WelcomeSliderFragment : Fragment() {
    private var _binding: FragmentWelcomeSliderBinding? = null
    private val binding get() = _binding!!
    private lateinit var sliderAdapter: WelcomeSliderAdapter
    private val nav by lazy { activity?.findNavController(R.id.main_content) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeSliderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sliderAdapter = WelcomeSliderAdapter(getData()) {
            nav?.navigate(WelcomeSliderFragmentDirections.actionWelcomeSliderFragmentToHomeFragment())
        }
        binding.viewPager.adapter = sliderAdapter
        binding.dotsContainer.setViewPager(binding.viewPager)
    }

    private fun getData() =
        listOf<SliderItem>(
            SliderItem(
                1, R.drawable.ic_sunny, "Hari ini merupakan hari yang sangat cerah"
            ),
            SliderItem(
                2, R.drawable.ic_broken_cloud, "Hari ini merupakan hari yang sangat berawan"
            ),
            SliderItem(
                3, R.drawable.ic_day_rain, "Hujan hari ini akan berlangsung selama 24 jam"
            )
        )
}