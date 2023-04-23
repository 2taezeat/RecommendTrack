package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.TokenDto
import com.example.recommendtrack.data.remote.TokenApi
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRemoteDataSourceImp @Inject constructor(private val tokenApi: TokenApi) :
    TokenRemoteDataSource {

    override suspend fun fetchToken(
        grantType: String, clientId: String, clientSecret: String
    ): ApiResponse<TokenDto> {
        return tokenApi.getToken(grantType, clientId, clientSecret)
    }


}