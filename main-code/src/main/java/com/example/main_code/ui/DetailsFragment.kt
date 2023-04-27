package com.example.main_code.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.main_code.R
import com.example.main_code.databinding.ActivityAppBinding
import com.example.main_code.databinding.FragmentDetailsBinding
import com.example.main_code.network.ServiceApi
import com.example.main_code.viewmodel.AppViewModel

class DetailsFragment : Fragment() {
    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    private val charsViewModel by lazy {
        ViewModelProvider(requireActivity())[AppViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        charsViewModel.charSelected?.let {
            binding.apply {
                charTitle.text = it.text?.split("-")?.get(0) ?: "Invalid name"
                charDescription.text = it.text ?: "No description"

                Glide
                    .with(this.root)
                    .load("${ServiceApi.IMAGE_BASE_URL}${it.icon?.uRL}")
                    .error(R.drawable.drawable)
                    .placeholder(R.drawable.loading)
                    .into(charImage)
            }
        }

        return binding.root
    }
}