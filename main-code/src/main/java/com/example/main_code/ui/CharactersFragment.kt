package com.example.main_code.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.main_code.R
import com.example.main_code.databinding.ActivityAppBinding
import com.example.main_code.databinding.FragmentCharactersBinding
import com.example.main_code.ui.adapter.AppAdapter
import com.example.main_code.util.AppState
import com.example.main_code.viewmodel.AppViewModel

class CharactersFragment : Fragment() {
    private val binding by lazy {
        FragmentCharactersBinding.inflate(layoutInflater)
    }

    private val charsViewModel by lazy {
        ViewModelProvider(requireActivity())[AppViewModel::class.java]
    }

    private val charAdapter by lazy {
        AppAdapter {
            charsViewModel.charSelected = it
            findNavController().navigate(R.id.action_SearchFragment_to_DetailsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.charRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = charAdapter
        }

        binding.searchChar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    charsViewModel.searchItem(it)
                }
                return false
            }

        })

        charsViewModel.search.observe(viewLifecycleOwner) {
            it?.let {
                charAdapter.updateChars(it)
            }
        }

        charsViewModel.chars.observe(viewLifecycleOwner) {
            when(it) {
                is AppState.Loading -> {

                }
                is AppState.Success -> {
                    charsViewModel.prevData = it.data
                    charAdapter.updateChars(it.data)
                }
                is AppState.Error -> {
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error")
                        .setMessage(it.error.localizedMessage)
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }

        if (charsViewModel.prevData == null) {
            charsViewModel.getCharacters()
        }

        return binding.root
    }
}