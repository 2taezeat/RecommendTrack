package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.GenresDto
import com.skydoves.sandwich.ApiResponse

interface GenreRemoteDataSource {
    suspend fun fetchGenres(accessToken: String): ApiResponse<GenresDto>
}
