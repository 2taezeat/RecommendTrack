package com.example.recommendtrack.presentation.song

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendtrack.databinding.ItemSearchSongLoadStateBinding


class SearchSongLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<SearchSongLoadStateAdapter.SearchSongLoadStateViewHolder>() {

    inner class SearchSongLoadStateViewHolder(
        private val binding: ItemSearchSongLoadStateBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.songLoadStateRetryBtn.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.songLoadStateRetryBtn.isVisible = loadState is LoadState.Error
        }
    }

    override fun onBindViewHolder(holder: SearchSongLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): SearchSongLoadStateViewHolder {
        return SearchSongLoadStateViewHolder(
            ItemSearchSongLoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            retry
        )
    }
}