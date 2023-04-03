package com.example.recommendtrack.presentation.artist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentArtistBinding
import com.example.recommendtrack.presentation.ui.BaseFragment
import com.example.recommendtrack.presentation.ui.artist.ArtistViewModel


class ArtistFragment : BaseFragment<FragmentArtistBinding>(R.layout.fragment_artist) {
    private val viewModel by viewModels<ArtistViewModel>()


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