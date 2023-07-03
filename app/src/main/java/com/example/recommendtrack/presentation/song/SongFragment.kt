package com.example.recommendtrack.presentation.song

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentSongBinding
import com.example.recommendtrack.presentation.BaseFragment
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class SongFragment : BaseFragment<FragmentSongBinding>(R.layout.fragment_song) {
    private val songViewModel by viewModels<SongViewModel>()

    private lateinit var songSearchView: SearchView
    private lateinit var songSearchBar: SearchBar
    private lateinit var songSearchRecyclerView: RecyclerView
    private lateinit var searchSongAdapter: SearchSongAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("${songViewModel}")

        initSearchView()
        initRecyclerView()

        songViewModel.searchedSongs.observe(viewLifecycleOwner, Observer { it ->
            searchSongAdapter.submitList(it)
        })
    }




    private fun initSearchView() {
        songSearchView = binding.songSearchView
        songSearchBar = binding.songSearchBar

        songSearchView.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(searchString: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (searchString.length >= 3) {
                    Timber.d("${searchString}")
                    songViewModel.searchSong(searchString.toString())

                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun initRecyclerView() {
        searchSongAdapter = SearchSongAdapter()


        songSearchRecyclerView = binding.searchSongRV.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = searchSongAdapter

        }



    }


    companion object {
        fun newInstance() = SongFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}



