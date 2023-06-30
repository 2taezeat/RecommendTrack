package com.example.recommendtrack.domain.repository

import com.example.recommendtrack.domain.entity.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    suspend fun fetchGenres(accessToken: String, onError: (String) -> Unit): Flow<List<Genre>>

    suspend fun fetchMyGenres(): Flow<List<Genre>>

    suspend fun addMyGenres(myGenres : List<Genre>)

    suspend fun deleteMyGenres(deletingGenres : List<Genre>)

}