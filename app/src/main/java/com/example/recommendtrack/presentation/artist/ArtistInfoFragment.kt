package com.example.recommendtrack.presentation.artist

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentArtistInfoBinding
import com.example.recommendtrack.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArtistInfoFragment: BaseFragment<FragmentArtistInfoBinding>(R.layout.fragment_artist_info) {

    private lateinit var artistNameTextView: TextView
    private lateinit var artistFollowersTextView: TextView
    private lateinit var artistPopularityTextView: TextView
    private lateinit var artistGenresTextView: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        artistNameTextView = binding.includeArtistInfoLayout.artistNameContentTv
        artistFollowersTextView = binding.includeArtistInfoLayout.artistFollowersContentTv
        artistPopularityTextView = binding.includeArtistInfoLayout.artistPopularityContentTv
        artistGenresTextView = binding.includeArtistInfoLayout.artistGenresContentTv
    }




}