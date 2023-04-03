package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.Genre
import com.example.recommendtrack.data.remote.GenreApi
import retrofit2.Call

class GenreRemoteDataSourceImp(private val genreApi: GenreApi): GenreRemoteDataSource {

    override suspend fun fetchGenres(): Call<Genre> {
        return genreApi.getGenres()
    }
}