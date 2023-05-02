package com.example.recommendtrack.domain.repository

import com.example.recommendtrack.domain.entity.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    suspend fun fetchGenres(accessToken: String): Flow<List<Genre>>

    suspend fun fetchMyGenres(): Flow<List<Genre>>

    suspend fun addMyGenres(myGenres : List<Genre>)

    suspend fun deleteMyGenres(myGenres : List<Genre>)

    suspend fun updateMyGenres(myGenres : List<Genre>)


}