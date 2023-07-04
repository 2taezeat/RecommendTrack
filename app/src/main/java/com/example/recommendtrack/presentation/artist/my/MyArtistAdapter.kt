package com.example.recommendtrack.presentation.artist.my

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recommendtrack.databinding.ItemMyArtistBinding
import com.example.recommendtrack.domain.entity.Artist
import timber.log.Timber
import java.util.Collections

class MyArtistAdapter(private val myArtistUpdateCallBack: MyArtistUpdateCallBack?): ListAdapter<Artist, MyArtistViewHolder>(
    artistDiffUtilCallBack
), ItemTouchHelperListener {



    private var onItemClickListener: ((Artist) -> Unit)? = null

    fun setOnItemClickListener(lambdaListener: (Artist) -> Unit) {
        onItemClickListener = lambdaListener
    }



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
        val itemMyArtistBinding = ItemMyArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyArtistViewHolder(itemMyArtistBinding, myArtistUpdateCallBack, onItemClickListener)

    }

    override fun onBindViewHolder(holder: MyArtistViewHolder, position: Int) {
        val myArtist = currentList[position]
        holder.bind(artist = myArtist)
    }

    override fun onItemMove(from: Int, to: Int) {
        val newList = currentList.toMutableList()
        Collections.swap(newList, from, to)
        Timber.d("${ from}, ${to}")
        submitList(newList)
    }


}