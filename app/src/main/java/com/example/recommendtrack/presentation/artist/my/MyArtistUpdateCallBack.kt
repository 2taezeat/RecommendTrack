package com.example.recommendtrack.presentation.artist.my

import com.example.recommendtrack.domain.entity.Artist

interface MyArtistUpdateCallBack {

    fun deleteMyArtist(deleteArtist: Artist)

    fun addMyArtist(myArtist: Artist)

    fun getMyArtist()

}