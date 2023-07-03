package com.example.recommendtrack.data.remote

import com.example.recommendtrack.data.dto.SongDto
import com.example.recommendtrack.utils.Constants.TRACK_STRING_VALUE
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SongApi {

    @GET("search")
    suspend fun getSong(
        @Header("Authorization") accessToken: String,
        @Query("q") songName: String,
        @Query("type") type: String = TRACK_STRING_VALUE,
        @Query("limit") limit : Int,
        @Query("offset") offset : Int,
    ): ApiResponse<SongDto>

}