package com.example.recommendtrack.presentation.artist

import androidx.recyclerview.widget.RecyclerView
import com.example.recommendtrack.databinding.ItemMyArtistBinding
import com.example.recommendtrack.domain.entity.Artist

class MyArtistViewHolder(private val itemMyArtistBinding: ItemMyArtistBinding) :
    RecyclerView.ViewHolder(itemMyArtistBinding.root) {

    fun bind(artist: Artist) {

        itemView.apply {
            itemMyArtistBinding.myArtistNameTV.text = artist.name
        }

    }


}