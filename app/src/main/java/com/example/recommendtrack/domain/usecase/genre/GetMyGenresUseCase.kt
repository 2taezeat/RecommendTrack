package com.example.recommendtrack.domain.usecase.genre

import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow

class GetMyGenresUseCase(private val genreRepository: GenreRepository) {
    suspend operator fun invoke(): Flow<List<Genre>> {
        return genreRepository.fetchMyGenres()
    }
}