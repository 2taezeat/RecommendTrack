package com.example.recommendtrack.presentation.artist.my

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recommendtrack.databinding.ItemMyArtistBinding
import com.example.recommendtrack.domain.entity.Artist
import com.google.android.material.snackbar.Snackbar


class MyArtistViewHolder(val itemMyArtistBinding: ItemMyArtistBinding, private val myArtistUpdateCallBack: MyArtistUpdateCallBack?, private val onItemClickListener: ((Artist) -> Unit)?) :
    RecyclerView.ViewHolder(itemMyArtistBinding.root) {



    fun bind(artist: Artist) {
        itemView.apply {
            setOnClickListener { onItemClickListener?.let { it(artist) } }

            itemMyArtistBinding.myArtistNameTV.text = artist.name

            Glide.with(context)
                .load(artist.imageUrl)
                .skipMemoryCache(true)
                .override(60,60)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC) // default
                .into(itemMyArtistBinding.myArtistImageIV)

            itemMyArtistBinding.myArtistFavoriteIV.setOnClickListener {
                myArtistUpdateCallBack?.deleteMyArtist(deleteArtist = artist)
                Snackbar.make(it, "ItemInTrack $layoutPosition touched!", Snackbar.LENGTH_LONG).show()
                myArtistUpdateCallBack?.getMyArtist()
            }
        }
    }




}