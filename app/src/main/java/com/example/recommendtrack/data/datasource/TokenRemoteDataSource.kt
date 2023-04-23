package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.GenresDto
import com.example.recommendtrack.data.dto.TokenDto
import kotlinx.coroutines.flow.Flow

interface TokenRemoteDataSource {
    suspend fun fetchToken(): Flow<TokenDto>
}