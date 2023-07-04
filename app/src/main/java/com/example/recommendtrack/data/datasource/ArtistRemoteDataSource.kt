package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.ArtistDto
import com.skydoves.sandwich.ApiResponse

interface ArtistRemoteDataSource {
    suspend fun searchArtist(accessToken: String, artistName: String): ApiResponse<ArtistDto>
}