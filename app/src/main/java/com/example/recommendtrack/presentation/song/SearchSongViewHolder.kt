package com.example.recommendtrack.presentation.song

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recommendtrack.databinding.ItemSearchSongBinding
import com.example.recommendtrack.domain.entity.Song

class SearchSongViewHolder(private val itemSearchSongBinding: ItemSearchSongBinding): RecyclerView.ViewHolder(itemSearchSongBinding.root) {
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


