package com.example.recommendtrack.data.repositoryImp

import android.util.Log
import com.example.recommendtrack.data.datasource.TokenRemoteDataSource
import com.example.recommendtrack.data.mapper.ErrorEnvelopeMapper
import com.example.recommendtrack.data.mapper.TokenMapper
import com.example.recommendtrack.domain.entity.Token
import com.example.recommendtrack.domain.repository.TokenRepository
import com.skydoves.sandwich.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class TokenRepositoryImp @Inject constructor(
    private val dataSource: TokenRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : TokenRepository {
    override suspend fun fetchToken(grantType: String, clientId: String, clientSecret: String): Flow<Token> {
        val response = dataSource.fetchToken(grantType, clientId, clientSecret)

        val flowToken = flow {
            response.suspendOnSuccess(TokenMapper) {
                val token = this
                Timber.tag("success").d( "$token")
                emit(token)
            }.suspendOnFailure {
                Timber.tag("fail").d( "${this.message()}")
            }.suspendOnError(ErrorEnvelopeMapper) {
                val errorMessage = this.message
                Timber.tag("error").d( "$errorMessage")
            }

        }.flowOn(ioDispatcher)
        return flowToken
    }
}