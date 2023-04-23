package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.GenresDto
import com.example.recommendtrack.data.remote.GenreApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRemoteDataSourceImp @Inject constructor(private val genreApi: GenreApi) :
    GenreRemoteDataSource {

    override suspend fun fetchGenres(): Flow<GenresDto> {
        return genreApi.getGenres()
    }


}