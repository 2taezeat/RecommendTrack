package com.example.recommendtrack.data.remote

import com.example.recommendtrack.data.dto.ArtistDto
import com.example.recommendtrack.utils.Constants.ARTIST_STRING_VALUE
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ArtistApi {

    @GET("search")
    suspend fun getArtist(
        @Header("Authorization") accessToken: String,
        @Query("q") artistName: String,
        @Query("type") type: String = ARTIST_STRING_VALUE,
        @Query("limit") limit : Int = 1,
        @Query("offset") offset : Int = 0,
    ): ApiResponse<ArtistDto>

}