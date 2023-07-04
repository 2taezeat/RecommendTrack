package com.example.recommendtrack.data.dto.searchDto.trackSearch

import com.example.recommendtrack.data.dto.searchDto.ExternalUrls

data class ArtistInTrack(
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)