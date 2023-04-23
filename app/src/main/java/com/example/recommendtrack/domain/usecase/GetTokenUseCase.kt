package com.example.recommendtrack.domain.usecase

import com.example.recommendtrack.domain.repository.TokenRepository

class GetTokenUseCase(private val tokenRepository: TokenRepository) {
    suspend fun invoke(
        grantType: String,
        clientId: String,
        clientSecret: String
    ) {
        tokenRepository.fetchToken(grantType, clientId, clientSecret)
    }
}
