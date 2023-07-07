package com.example.recommendtrack.presentation.artist.my

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recommendtrack.databinding.ItemMyArtistBinding
import com.example.recommendtrack.domain.entity.Artist
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import java.util.Collections

class MyArtistAdapter(private val myArtistUpdateCallBack: MyArtistUpdateCallBack?): ListAdapter<Artist, MyArtistAdapter.MyArtistViewHolder>(
    artistDiffUtilCallBack
), ItemTouchHelperListener {



    private var onItemClickListener: ((Artist) -> Unit)? = null

    fun setOnItemClickListener(lambdaListener: (Artist) -> Unit) {
        onItemClickListener = lambdaListener
    }


    inner class MyArtistViewHolder(val itemMyArtistBinding: ItemMyArtistBinding, private val myArtistUpdateCallBack: MyArtistUpdateCallBack?, private val onItemClickListener: ((Artist) -> Unit)?) :
        RecyclerView.ViewHolder(itemMyArtistBinding.root) {

        init {
            Timber.d("init, ${absoluteAdapterPosition}")
            itemView.setOnClickListener {
                onItemClickListener?.let { it(currentList[absoluteAdapterPosition]) }
            }
        }


        fun bind(artist: Artist) {
            itemView.apply {
                //setOnClickListener { onItemClickListener?.let { it(artist) } }
                Timber.d("bind, ${absoluteAdapterPosition}")
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