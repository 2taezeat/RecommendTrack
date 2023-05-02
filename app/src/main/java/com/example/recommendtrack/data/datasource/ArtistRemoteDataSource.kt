package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.ArtistDto
import com.example.recommendtrack.data.dto.GenresDto
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface ArtistRemoteDataSource {
    suspend fun searchArtist(accessToken: String, artistName: String): ApiResponse<ArtistDto>
}