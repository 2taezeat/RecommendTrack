package com.example.recommendtrack.domain.usecase.artist

import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.repository.ArtistRepository
import com.example.recommendtrack.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow

class AddMyArtistUseCase(private val artistRepository: ArtistRepository) {
    suspend fun invoke(myArtist : Artist) {
        return artistRepository.addMyArtist(myArtist)
    }
}