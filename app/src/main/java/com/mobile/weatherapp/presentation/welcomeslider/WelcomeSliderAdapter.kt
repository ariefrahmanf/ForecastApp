package com.mobile.weatherapp.presentation.welcomeslider

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.weatherapp.databinding.WelcomeSliderItemBinding
import com.mobile.weatherapp.utils.SliderItem

class WelcomeSliderAdapter(val list: List<SliderItem>, val onClick: () -> Unit?) :
    RecyclerView.Adapter<WelcomeSliderAdapter.WelcomeSliderViewHolder>() {
    inner class WelcomeSliderViewHolder(val binding: WelcomeSliderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SliderItem) {
            binding.apply {
                Glide.with(binding.root)
                    .load(item.image)
                    .into(ivItem)
                tvItem.text = item.text
                if (adapterPosition == list.size - 1) {
                    btnNext.visibility = View.VISIBLE
                } else {
                    btnNext.visibility = View.GONE
                }
                btnNext.setOnClickListener {
                    onClick.invoke()
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WelcomeSliderAdapter.WelcomeSliderViewHolder {
        val view =
            WelcomeSliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WelcomeSliderViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: WelcomeSliderAdapter.WelcomeSliderViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

