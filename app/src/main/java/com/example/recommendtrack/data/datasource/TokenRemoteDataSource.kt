package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.TokenDto
import com.skydoves.sandwich.ApiResponse

interface TokenRemoteDataSource {
    suspend fun fetchToken(grantType: String, clientId: String, clientSecret: String): ApiResponse<TokenDto>
}