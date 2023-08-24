package com.example.recommendtrack.presentation.song

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recommendtrack.databinding.ItemSearchSongBinding
import com.example.recommendtrack.domain.entity.Song

class SearchSongPagingAdapter(): PagingDataAdapter<Song, SearchSongPagingAdapter.SearchSongViewHolder>(songSearchDiffUtilCallBack) {

    inner class SearchSongViewHolder(private val itemSearchSongBinding: ItemSearchSongBinding): RecyclerView.ViewHolder(itemSearchSongBinding.root) {
        fun bind(song: Song) {
            itemView.apply {
                itemSearchSongBinding.songSearchSongNameTV.text = song.name
                val artistsName = song.artists.map { it.name }
                itemSearchSongBinding.songSearchArtistTV.text = artistsName.toString()

                Glide.with(context)
                    .load(song.album.images[0].url)
                    .skipMemoryCache(true)
                    .override(60,60)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC) // default
                    .into(itemSearchSongBinding.songSearchImageIV)
            }


        }
    }



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
        val pagedSong = getItem(position)
        pagedSong?.let { holder.bind(pagedSong) }

    }
}

