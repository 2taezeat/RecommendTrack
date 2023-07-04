package com.example.recommendtrack.domain.usecase.genre

import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.repository.GenreRepository

class AddMyGenresUseCase(private val genreRepository: GenreRepository) {
    suspend operator fun invoke(myGenres : List<Genre>) {
        return genreRepository.addMyGenres(myGenres)
    }
}