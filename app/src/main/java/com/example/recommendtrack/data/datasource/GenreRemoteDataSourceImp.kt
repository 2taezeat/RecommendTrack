package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.GenresDto
import com.example.recommendtrack.data.remote.GenreApi
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRemoteDataSourceImp @Inject constructor(private val genreApi: GenreApi) :
    GenreRemoteDataSource {

    override suspend fun fetchGenres(): ApiResponse<GenresDto> {
        return genreApi.getGenres()
    }


}