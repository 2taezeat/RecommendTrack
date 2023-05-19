package com.example.recommendtrack.presentation.artist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
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
    private lateinit var artistIsMyCheckBox: CheckBox
    private lateinit var myArtistNaviButton: Button

    private var myArtistUpdateCallBack: MyArtistUpdateCallBack? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        myArtistUpdateCallBack = context as MyArtistUpdateCallBack?

    }


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

            artistIsMyCheckBox.isChecked = viewModel.myArtists.value!!.contains(it)
        })

    }

    override fun onDetach() {
        super.onDetach()
        myArtistUpdateCallBack = null

    }


    private fun initView() {
        initSearchView()
        initClickAbleView()
        initContentTextView()

    }

    private fun initClickAbleView() {
        artistIsMyCheckBox = binding.artistIsMyCheckBox
        myArtistNaviButton = binding.myArtistNaviButton



        myArtistNaviButton.setOnClickListener {
            val action = ArtistFragmentDirections.actionArtistFragmentToMyArtistFragment()
            findNavController().navigate(action)
        }


        artistIsMyCheckBox.setOnClickListener { it ->
            if (artistIsMyCheckBox.isChecked) {
                viewModel.searchArtist.value?.let {
                    myArtistUpdateCallBack?.addMyArtist(it)
                    myArtistUpdateCallBack?.getMyArtist()
                }
            } else {
                viewModel.searchArtist.value?.let {
                    myArtistUpdateCallBack?.deleteMyArtist(it)
                    myArtistUpdateCallBack?.getMyArtist()
                }
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