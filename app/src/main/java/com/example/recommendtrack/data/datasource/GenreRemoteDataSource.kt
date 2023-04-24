package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.GenresDto
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface GenreRemoteDataSource {
    suspend fun fetchGenres(): ApiResponse<GenresDto>
}