package com.example.recommendtrack.domain.repository

import com.example.recommendtrack.domain.entity.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    suspend fun fetchGenres(): Flow<List<Genre>>
}