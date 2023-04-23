package com.example.recommendtrack.data.dto

import com.google.gson.annotations.SerializedName

data class RequestToken(
    @SerializedName("grant_type")
    val grantType : String,
    @SerializedName("client_id")
    val clientId : String,
    @SerializedName("client_secret")
    val clientSecret : String
)

