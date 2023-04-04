package com.example.recommendtrack.presentation.ui.artist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentGenreBinding
import com.example.recommendtrack.presentation.ui.BaseFragment


class GenreFragment : BaseFragment<FragmentGenreBinding>(R.layout.fragment_genre) {
    private val viewModel by viewModels<GenreViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("viewModel_GenreFragment", "${viewModel}")

        viewModel.getAllGenres()

    }


    companion object {
        fun newInstance() =
            GenreFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}