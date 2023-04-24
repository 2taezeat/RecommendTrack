package com.example.recommendtrack.domain.usecase

import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow

class GetAllGenreUseCase(private val genreRepository: GenreRepository) {
    suspend fun invoke(accessToken: String): Flow<List<Genre>> {
        return genreRepository.fetchGenres(accessToken)
    }
}