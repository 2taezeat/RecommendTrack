package com.example.recommendtrack.presentation.artist

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentArtistBinding
import com.example.recommendtrack.presentation.ui.BaseFragment
import com.example.recommendtrack.presentation.ui.artist.ArtistViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ArtistFragment : BaseFragment<FragmentArtistBinding>(R.layout.fragment_artist) {
    private val viewModel by viewModels<ArtistViewModel>()
    private lateinit var searchView: SearchView
    private lateinit var artistNameTextView: TextView
    private lateinit var artistFollowersTextView: TextView
    private lateinit var artistPopularityTextView: TextView
    private lateinit var artistGenresTextView: TextView
    private lateinit var addMyArtistButton: Button
    private lateinit var deleteMyArtistButton: Button
    private lateinit var isMyButton: Button


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

            if (viewModel.myArtists.value!!.contains(it)) {
                isMyButton.text = "isMyAdded"
            } else {
                isMyButton.text = "Not_isMyAdded"
            }

        })

        viewModel.myArtists.observe(viewLifecycleOwner, Observer { it ->

        })


    }

    private fun initView() {
        initSearchView()
        initButtonView()
        initContentTextView()
    }

    private fun initButtonView() {
        addMyArtistButton = binding.artistAddButton
        deleteMyArtistButton = binding.artistDeleteButton
        isMyButton = binding.artistIsMyButton

        addMyArtistButton.setOnClickListener {
            viewModel.searchArtist.value?.let {
                viewModel.addMyArtist(it)
            }
        }

        deleteMyArtistButton.setOnClickListener {
            viewModel.searchArtist.value?.let {
                viewModel.deleteMyArtist(it)
            }
        }

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
                    viewModel.searchArtist(searchString)
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