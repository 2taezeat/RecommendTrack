package com.example.recommendtrack.domain.usecase.artist

import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.repository.ArtistRepository
import com.example.recommendtrack.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow

class DeleteMyArtistUseCase(private val artistRepository: ArtistRepository) {
    suspend fun invoke(deletingArtist : Artist) {
        return artistRepository.deleteMyArtist(deletingArtist)
    }
}