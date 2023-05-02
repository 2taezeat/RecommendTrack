package com.example.recommendtrack.data.remote

import com.example.recommendtrack.data.dto.GenresDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ArtistApi {

    @GET("search/")
    suspend fun getArtist(
        @Header("Authorization") accessToken: String
    ): ApiResponse<GenresDto>

}