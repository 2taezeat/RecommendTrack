package com.example.recommendtrack.presentation.artist

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recommendtrack.databinding.ItemMyArtistBinding
import com.example.recommendtrack.domain.entity.Artist


class MyArtistViewHolder(private val itemMyArtistBinding: ItemMyArtistBinding, private val myArtistUpdateCallBack: MyArtistUpdateCallBack?) :
    RecyclerView.ViewHolder(itemMyArtistBinding.root) {

    fun bind(artist: Artist) {
        itemView.apply {
            itemMyArtistBinding.myArtistNameTV.text = artist.name

            Glide.with(context)
                .load(artist.imageUrl)
                .skipMemoryCache(true)
                .override(60,60)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC) // default
                .into(itemMyArtistBinding.myArtistImageIV)

            itemMyArtistBinding.myArtistFavoriteIV.setOnClickListener {
                myArtistUpdateCallBack?.deleteMyArtist(deleteArtist = artist)
            }

        }
    }




}