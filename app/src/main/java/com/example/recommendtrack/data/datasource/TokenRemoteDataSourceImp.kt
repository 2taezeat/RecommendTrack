package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.GenresDto
import com.example.recommendtrack.data.dto.RequestToken
import com.example.recommendtrack.data.dto.TokenDto
import com.example.recommendtrack.data.remote.GenreApi
import com.example.recommendtrack.data.remote.TokenApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRemoteDataSourceImp @Inject constructor(private val tokenApi: TokenApi) :
    TokenRemoteDataSource {

    override suspend fun fetchToken(requestToken: RequestToken): Flow<TokenDto> {
        return tokenApi.getToken(requestToken)
    }


}