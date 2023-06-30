package com.example.recommendtrack.domain.usecase.artist

import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow


class SearchArtistUseCase(private val artistRepository: ArtistRepository) {
    suspend fun invoke(accessToken: String, artistName: String, onError: (String) -> Unit ): Flow<Artist> {
        return artistRepository.searchArtist(accessToken, artistName, onError)

    }
}
