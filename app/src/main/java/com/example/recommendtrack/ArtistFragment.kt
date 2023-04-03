package com.example.recommendtrack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.recommendtrack.databinding.FragmentArtistBinding


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