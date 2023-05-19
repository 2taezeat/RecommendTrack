package com.example.recommendtrack.presentation.artist

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.navArgs
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

    private val args by navArgs<ArtistInfoFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initContent()
    }

    private fun initView() {
        artistNameTextView = binding.includeArtistInfoLayout.artistNameContentTv
        artistFollowersTextView = binding.includeArtistInfoLayout.artistFollowersContentTv
        artistPopularityTextView = binding.includeArtistInfoLayout.artistPopularityContentTv
        artistGenresTextView = binding.includeArtistInfoLayout.artistGenresContentTv
    }


    private fun initContent() {
        val artist = args.artist
        artistNameTextView.text = artist.name
        artistFollowersTextView.text = artist.followers.toString()
        artistPopularityTextView.text = artist.popularity.toString()
        artistGenresTextView.text = artist.genres.toString()
    }
}