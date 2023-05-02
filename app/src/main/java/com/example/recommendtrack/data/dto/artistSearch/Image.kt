package com.example.recommendtrack.data.dto.artistSearch

import com.google.gson.annotations.SerializedName


data class Image(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)