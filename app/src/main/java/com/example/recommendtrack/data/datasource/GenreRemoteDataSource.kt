package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.GenresDto
import kotlinx.coroutines.flow.Flow

interface GenreRemoteDataSource {
    suspend fun fetchGenres(): Flow<GenresDto>
}