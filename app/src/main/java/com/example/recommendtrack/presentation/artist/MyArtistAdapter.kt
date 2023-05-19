package com.example.recommendtrack.presentation.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recommendtrack.databinding.ItemMyArtistBinding
import com.example.recommendtrack.domain.entity.Artist

class MyArtistAdapter(private val myArtistUpdateCallBack: MyArtistUpdateCallBack?): ListAdapter<Artist, MyArtistViewHolder>(artistDiffUtilCallBack) {

    companion object {
        private val artistDiffUtilCallBack = object : DiffUtil.ItemCallback<Artist>() {
            override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
               return oldItem == newItem
            }
        }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyArtistViewHolder {
        return MyArtistViewHolder(ItemMyArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false), myArtistUpdateCallBack)

    }

    override fun onBindViewHolder(holder: MyArtistViewHolder, position: Int) {
        val myArtist = currentList[position]
        holder.bind(artist = myArtist)

    }
}