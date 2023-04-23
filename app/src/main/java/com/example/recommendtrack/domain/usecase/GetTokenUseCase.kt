package com.example.recommendtrack.domain.usecase

import com.example.recommendtrack.data.dto.RequestToken
import com.example.recommendtrack.domain.repository.GenreRepository
import com.example.recommendtrack.domain.repository.TokenRepository

class GetTokenUseCase(private val tokenRepository: TokenRepository) {
    suspend fun invoke(requestToken: RequestToken) = tokenRepository.fetchToken(requestToken)
}
