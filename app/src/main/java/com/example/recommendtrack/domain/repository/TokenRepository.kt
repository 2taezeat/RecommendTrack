package com.example.recommendtrack.domain.repository

import com.example.recommendtrack.domain.entity.Token
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun fetchToken(
        grantType: String,
        clientId: String,
        clientSecret: String
    ): Flow<Token>
}