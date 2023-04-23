package com.example.recommendtrack.data.repositoryImp

import com.example.recommendtrack.data.datasource.GenreRemoteDataSource
import com.example.recommendtrack.data.datasource.TokenRemoteDataSource
import com.example.recommendtrack.data.dto.GenresDto
import com.example.recommendtrack.data.dto.TokenDto
import com.example.recommendtrack.data.mapper.asDomain
import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.entity.Token
import com.example.recommendtrack.domain.repository.GenreRepository
import com.example.recommendtrack.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenRepositoryImp
@Inject constructor(private val dataSource: TokenRemoteDataSource) : TokenRepository {
    override suspend fun fetchToken(): Flow<Token> {
        return dataSource.fetchToken().map { tokenDto -> tokenDto.asDomain() }
    }
}