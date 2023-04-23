package com.example.recommendtrack.data.remote

import com.example.recommendtrack.data.dto.GenresDto
import com.example.recommendtrack.data.dto.RequestToken
import com.example.recommendtrack.data.dto.TokenDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface TokenApi {

    @POST("api/token")
    fun getToken(
        @Body requestToken: RequestToken
    ): Flow<TokenDto>

}