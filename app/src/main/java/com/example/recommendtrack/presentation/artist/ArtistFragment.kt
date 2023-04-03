package com.example.recommendtrack.presentation.ui.artist

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentArtistBinding
import com.example.recommendtrack.presentation.ui.BaseFragment


class ArtistFragment : BaseFragment<FragmentArtistBinding>(R.layout.fragment_artist, ArtistViewModel().javaClass) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("viewModel_ArtistFragment", "${viewModel}")
    }


    companion object {
        fun newInstance() =
            ArtistFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}