package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.Genre
import retrofit2.Call

interface GenreRemoteDataSource {
    suspend fun fetchGenres(): Call<Genre>
}