package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.SongDto
import com.skydoves.sandwich.ApiResponse

interface SongRemoteDataSource {
    suspend fun searchSong(accessToken: String, songName: String, limit: Int, offset: Int): ApiResponse<SongDto>
}