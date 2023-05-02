package com.example.recommendtrack.domain.usecase.artist

import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow


class GetArtistUseCase(private val artistRepository: ArtistRepository) {
    suspend fun invoke(accessToken: String, artistName: String): Flow<Artist> {
        return artistRepository.fetchArtist(accessToken, artistName)

    }
}
