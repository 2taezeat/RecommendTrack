package com.example.recommendtrack.presentation.ui.artist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentGenreBinding
import com.example.recommendtrack.presentation.ui.BaseFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
class GenreFragment : BaseFragment<FragmentGenreBinding>(R.layout.fragment_genre) {
    private val viewModel : GenreViewModel by viewModels()
    private lateinit var genreChipGroup: ChipGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllGenres()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("viewModel_GenreFragment", "${viewModel}")
        genreChipGroup = binding.genreChipGroup

        initGenreChipView()




    }


    companion object {
        fun newInstance() =
            GenreFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun initGenreChipView() {
        val chip = Chip(this.context)
        chip.text = "sample_test"

        genreChipGroup.addView(chip)
    }


}