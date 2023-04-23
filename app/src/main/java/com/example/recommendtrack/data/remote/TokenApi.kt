package com.example.recommendtrack.data.remote

import com.example.recommendtrack.data.dto.TokenDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

interface TokenApi {

    @POST("api/token")
    @FormUrlEncoded
    suspend fun getToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): ApiResponse<TokenDto>


}