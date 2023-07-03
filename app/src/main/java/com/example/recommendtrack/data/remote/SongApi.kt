package com.example.recommendtrack.data.remote

import com.example.recommendtrack.data.dto.SongDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SongApi {

    @GET("search")
    suspend fun getSong(
        @Header("Authorization") accessToken: String,
        @Query("q") songName: String,
        @Query("type") type: String = "artist",
        @Query("limit") limit : Int = 1,
        @Query("offset") offset : Int = 0,
    ): ApiResponse<SongDto>

}