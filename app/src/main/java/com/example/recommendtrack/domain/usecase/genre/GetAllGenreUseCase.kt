package com.example.recommendtrack.domain.usecase.genre

import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow

class GetAllGenreUseCase(private val genreRepository: GenreRepository) {
    suspend fun invoke(accessToken: String, onError: (String) -> Unit): Flow<List<Genre>> {
        return genreRepository.fetchGenres(accessToken, onError)
    }
}