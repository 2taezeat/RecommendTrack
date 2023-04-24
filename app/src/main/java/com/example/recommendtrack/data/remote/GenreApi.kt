package com.example.recommendtrack.data.remote

import com.example.recommendtrack.data.dto.GenresDto
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header

interface GenreApi {

    @GET("recommendations/available-genre-seeds")
    fun getGenres(
        @Header("Authorization") accessToken: String
    ): ApiResponse<GenresDto>

}