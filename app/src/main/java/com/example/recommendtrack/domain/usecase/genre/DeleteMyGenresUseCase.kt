package com.example.recommendtrack.domain.usecase.genre

import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow

class DeleteMyGenresUseCase(private val genreRepository: GenreRepository) {
    suspend fun invoke(deletingGenres : List<Genre>) {
        return genreRepository.deleteMyGenres(deletingGenres)
    }
}