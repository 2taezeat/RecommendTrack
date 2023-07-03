package com.example.recommendtrack.presentation.song

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recommendtrack.databinding.ItemSearchSongBinding
import com.example.recommendtrack.domain.entity.Song

class SearchSongAdapter(): ListAdapter<Song, SearchSongViewHolder>(songSearchDiffUtilCallBack) {
    companion object {
        private val songSearchDiffUtilCallBack = object : DiffUtil.ItemCallback<Song>() {
            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchSongViewHolder {
        return SearchSongViewHolder(ItemSearchSongBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchSongViewHolder, position: Int) {
        val searchedSong = currentList[position]
        holder.bind(searchedSong)
    }
}

