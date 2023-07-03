package com.example.recommendtrack.data.dto.searchDto.artistSearch

import com.google.gson.annotations.SerializedName


data class Followers(
    @SerializedName("href")
    val href: Any,
    @SerializedName("total")
    val total: Int
)