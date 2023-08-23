package com.example.recommendtrack.domain.usecase.token

import com.example.recommendtrack.domain.entity.Token
import com.example.recommendtrack.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow

class WorkerGetTokenUseCase(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke(
        grantType: String,
        clientId: String,
        clientSecret: String
    ): Flow<Token> {
        return tokenRepository.fetchToken(grantType, clientId, clientSecret)
    }
}
