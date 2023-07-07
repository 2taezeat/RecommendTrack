package com.example.recommendtrack.presentation.song

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
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
    private lateinit var searchSongAdapter: SearchSongPagingAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("${songViewModel}")


        initSearchAdapter()
        setSearchSongPagingLoadState()
        initRecyclerView()
        initSearchView()


        collectLatestStateFlow(songViewModel.searchedPagedSongs) { searchSongAdapter.submitData(it) }
    }

    private fun initSearchAdapter() {
        searchSongAdapter = SearchSongPagingAdapter()
        binding.searchViewRefreshBtn.setOnClickListener {
            searchSongAdapter.refresh()
        }

    }


    private fun initSearchView() {
        songSearchBar = binding.songSearchBar
        songSearchView = binding.songSearchView
        songSearchView.editText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(searchString: CharSequence, start: Int, before: Int, count: Int) {
                if (searchString.length >= 2) {
                    songViewModel.searchSongsPaging(searchString.toString())
                } else if (searchString.length <= 1) {
                    binding.songSearchNoResultTv.isVisible = true
                    songSearchRecyclerView.isVisible = false
                }

            }

            override fun afterTextChanged(p0: Editable?) {}
        })


    }

    private fun initRecyclerView() {
        songSearchRecyclerView = binding.searchSongRV.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = searchSongAdapter.withLoadStateFooter(
                footer = SearchSongLoadStateAdapter(searchSongAdapter::retry)
            )

        }

    }


    private fun setSearchSongPagingLoadState() {
        searchSongAdapter.addLoadStateListener { combinedLoadStates ->
            val loadState = combinedLoadStates.source
            val isSongsEmpty = searchSongAdapter.itemCount < 1
                    && loadState.refresh is LoadState.NotLoading
                    && loadState.append.endOfPaginationReached
            val isError = loadState.refresh is LoadState.Error || loadState.prepend is LoadState.Error

            binding.songSearchNoResultTv.isVisible = isSongsEmpty
            songSearchRecyclerView.isVisible = !isError
            binding.songSearchProgressBar.isVisible = loadState.refresh is LoadState.Loading
            binding.searchViewRefreshBtn.isVisible = isError
        }

    }



    companion object {
        fun newInstance() = SongFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}



