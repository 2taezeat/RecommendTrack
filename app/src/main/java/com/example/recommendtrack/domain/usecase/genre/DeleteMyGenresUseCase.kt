package com.example.recommendtrack.domain.usecase.genre

import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.repository.GenreRepository

class DeleteMyGenresUseCase(private val genreRepository: GenreRepository) {
    suspend operator fun invoke(deletingGenres : List<Genre>) {
        return genreRepository.deleteMyGenres(deletingGenres)
    }
}