package com.example.recommendtrack.presentation.artist

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentArtistBinding
import com.example.recommendtrack.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ArtistFragment : BaseFragment<FragmentArtistBinding>(R.layout.fragment_artist) {
    private val viewModel: ArtistViewModel by activityViewModels()
    private lateinit var searchView: SearchView
    private lateinit var artistNameTextView: TextView
    private lateinit var artistFollowersTextView: TextView
    private lateinit var artistPopularityTextView: TextView
    private lateinit var artistGenresTextView: TextView
    private lateinit var addMyArtistButton: Button
    private lateinit var deleteMyArtistButton: Button
    private lateinit var isMyArtist: ImageView
    private lateinit var myArtistNaviButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("${viewModel}")
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
                isMyArtist.setImageResource(R.drawable.baseline_favorite_24)
            } else {
                isMyArtist.setImageResource(R.drawable.baseline_favorite_border_24)
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
        isMyArtist = binding.artistIsMyIV
        myArtistNaviButton = binding.myArtistNaviButton

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

        myArtistNaviButton.setOnClickListener {
            val action = ArtistFragmentDirections.actionArtistFragmentToMyArtistFragment(myArtistList = viewModel.myArtists.value!!.toTypedArray())
            findNavController().navigate(action)
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