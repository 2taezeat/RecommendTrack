package com.example.recommendtrack.data.repositoryImp

import com.example.recommendtrack.data.datasource.TokenRemoteDataSource
import com.example.recommendtrack.data.mapper.ErrorEnvelopeMapper
import com.example.recommendtrack.data.mapper.TokenMapper
import com.example.recommendtrack.domain.entity.Token
import com.example.recommendtrack.domain.repository.TokenRepository
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
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

        val tokenFlow = flow {
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
        return tokenFlow
    }
}