package com.example.recommendtrack.domain.usecase

import com.example.recommendtrack.domain.repository.GenreRepository

class GetAllGenreUseCase(private val genreRepository: GenreRepository) {
    suspend fun invoke() = genreRepository.fetchGenres()
}