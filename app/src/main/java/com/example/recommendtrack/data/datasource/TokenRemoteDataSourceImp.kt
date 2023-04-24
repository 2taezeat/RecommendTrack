package com.example.recommendtrack.data.datasource

import android.util.Log
import com.example.recommendtrack.data.dto.TokenDto
import com.example.recommendtrack.data.mapper.ErrorEnvelopeMapper
import com.example.recommendtrack.data.mapper.TokenMapper
import com.example.recommendtrack.data.remote.TokenApi
import com.example.recommendtrack.domain.entity.Token
import com.skydoves.sandwich.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRemoteDataSourceImp @Inject constructor(private val tokenApi: TokenApi) : TokenRemoteDataSource {
    override suspend fun fetchToken(
        grantType: String, clientId: String, clientSecret: String
    ): ApiResponse<TokenDto> {
        return tokenApi.getToken(grantType, clientId, clientSecret)
    }



}