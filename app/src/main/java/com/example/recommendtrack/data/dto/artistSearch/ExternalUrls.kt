package com.example.recommendtrack.data.dto.artistSearch

import com.google.gson.annotations.SerializedName


data class ExternalUrls(
    @SerializedName("spotify")
    val spotify: String
)