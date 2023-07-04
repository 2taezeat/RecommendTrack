package com.example.recommendtrack.data.dto.searchDto.artistSearch

import com.example.recommendtrack.data.dto.searchDto.ExternalUrls
import com.example.recommendtrack.data.dto.searchDto.Image
import com.google.gson.annotations.SerializedName


data class ItemInArtist(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val followers: Followers,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String
)