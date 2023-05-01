package com.example.recommendtrack.domain.usecase

import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow

class SaveMyGenresUseCase(private val genreRepository: GenreRepository) {
    suspend fun invoke(myGenres : List<Genre>) {
        return genreRepository.addMyGenres(myGenres)
    }
}