package com.example.recommendtrack.data.remote

import com.example.recommendtrack.data.dto.ArtistDto
import com.example.recommendtrack.data.dto.GenresDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ArtistApi {

    @GET("search")
    suspend fun getArtist(
        @Header("Authorization") accessToken: String,
        @Query("q") artistName: String,
        @Query("type") type: String = "artist",
        @Query("limit") limit : Int = 1,
        @Query("offset") offset : Int = 0,
    ): ApiResponse<ArtistDto>

}