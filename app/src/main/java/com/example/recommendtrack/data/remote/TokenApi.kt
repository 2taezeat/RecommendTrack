package com.example.recommendtrack.data.remote

import com.example.recommendtrack.data.dto.GenresDto
import com.example.recommendtrack.data.dto.TokenDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.POST

interface TokenApi {

    @POST("api/token")
    fun getToken(): Flow<TokenDto>

}