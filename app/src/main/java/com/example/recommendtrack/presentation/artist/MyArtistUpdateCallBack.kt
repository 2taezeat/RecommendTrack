package com.example.recommendtrack.presentation.artist

import com.example.recommendtrack.domain.entity.Artist

interface MyArtistUpdateCallBack {

    fun deleteMyArtist(deleteArtist: Artist)
}