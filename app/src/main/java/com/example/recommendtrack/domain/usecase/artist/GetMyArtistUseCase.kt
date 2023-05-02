package com.example.recommendtrack.domain.usecase.artist

import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow

class GetMyArtistUseCase(private val artistRepository: ArtistRepository) {
    suspend fun invoke(): Flow<List<Artist>> {
        return artistRepository.fetchMyArtist()
    }
}