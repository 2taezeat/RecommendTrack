package com.example.recommendtrack.domain.usecase.artist

import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.repository.ArtistRepository

class DeleteMyArtistUseCase(private val artistRepository: ArtistRepository) {
    suspend fun deleteOneArtist(deletingArtist : Artist) {
        return artistRepository.deleteMyArtist(deletingArtist)
    }


    suspend fun deleteAllArtists() {
        return artistRepository.deleteAllMyArtists()
    }
}