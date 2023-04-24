package com.example.recommendtrack.data.remote

import com.example.recommendtrack.data.dto.GenresDto
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface GenreApi {

    @GET("recommendations/available-genre-seeds")
    fun getGenres(): ApiResponse<GenresDto>

}