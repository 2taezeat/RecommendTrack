package com.example.recommendtrack.domain.usecase

import android.util.Log
import com.example.recommendtrack.domain.entity.Token
import com.example.recommendtrack.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class GetTokenUseCase(private val tokenRepository: TokenRepository) {
    suspend fun invoke(
        grantType: String,
        clientId: String,
        clientSecret: String
    ): Flow<Token> {
        return tokenRepository.fetchToken(grantType, clientId, clientSecret)
    }
}
