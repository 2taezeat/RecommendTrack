package com.example.recommendtrack.domain.usecase.artist

import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.repository.ArtistRepository

class AddMyArtistUseCase(private val artistRepository: ArtistRepository) {
    suspend fun addOneArtist(myArtist : Artist) {
        return artistRepository.addMyArtist(myArtist)
    }



    suspend fun addArtists(artists: List<Artist>) {
        return artistRepository.addMyArtists(artists)
    }
}