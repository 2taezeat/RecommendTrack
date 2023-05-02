package com.example.recommendtrack.presentation.artist

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.databinding.adapters.SearchViewBindingAdapter.setOnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentArtistBinding
import com.example.recommendtrack.presentation.ui.BaseFragment
import com.example.recommendtrack.presentation.ui.artist.ArtistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistFragment : BaseFragment<FragmentArtistBinding>(R.layout.fragment_artist) {
    private val viewModel by viewModels<ArtistViewModel>()
    private lateinit var searchView: SearchView
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


        viewModel.searchArtist.observe(viewLifecycleOwner, Observer { it ->
            artistNameTextView.text = it.name
            artistFollowersTextView.text = it.followers.toString()
            artistPopularityTextView.text = it.popularity.toString()
            artistGenresTextView.text = it.genres.toString()

        })


    }

    private fun initView() {
        initSearchView()
        initContentTextView()
    }

    private fun initContentTextView() {
        artistNameTextView = binding.artistNameContentTv
        artistFollowersTextView = binding.artistFollowersContentTv
        artistPopularityTextView = binding.artistPopularityContentTv
        artistGenresTextView = binding.artistGenresContentTv
    }


    private fun initSearchView() {
        searchView = binding.artistSearchView
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(searchString: String): Boolean {
                    viewModel.getArtist(searchString)
                    return true
                }

                override fun onQueryTextChange(searchString: String): Boolean {
                    return false
                }
            })

        }


    }


    companion object {
        fun newInstance() =
            ArtistFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}