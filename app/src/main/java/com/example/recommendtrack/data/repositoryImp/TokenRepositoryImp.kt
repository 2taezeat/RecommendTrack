package com.example.recommendtrack.data.repositoryImp

import android.util.Log
import com.example.recommendtrack.data.datasource.TokenRemoteDataSource
import com.example.recommendtrack.data.mapper.ErrorEnvelopeMapper
import com.example.recommendtrack.data.mapper.TokenMapper
import com.example.recommendtrack.domain.entity.Token
import com.example.recommendtrack.domain.repository.TokenRepository
import com.skydoves.sandwich.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TokenRepositoryImp @Inject constructor(private val dataSource: TokenRemoteDataSource) :
    TokenRepository {
    override suspend fun fetchToken(
        grantType: String,
        clientId: String,
        clientSecret: String
    ): Flow<Token> {

        //val response = dataSource.fetchToken(grantType, clientId, clientSecret)
        //Log.d("TokenRepositoryImp", "${response}")



        return flow {
            val response = dataSource.fetchToken(grantType, clientId, clientSecret)
            response.suspendOnSuccess(TokenMapper) {
                val token = this
                Log.d("TokenRepositoryImp", "$token")

                emit(token)
            }.suspendOnFailure {
                Log.d("TokenRepositoryImp", "${this.message()}")

            }.suspendOnError(ErrorEnvelopeMapper) {
                val errorMessage = this.message
                Log.d("TokenRepositoryImp", "$errorMessage")
            }


        }
    }
}