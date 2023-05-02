package com.example.recommendtrack.data.dto

import com.example.recommendtrack.data.dto.artistSearch.Artists
import com.google.gson.annotations.SerializedName


data class ArtistDto(
    @SerializedName("artists")
    val artists: Artists
)