package com.example.recommendtrack.data.dto.searchDto.trackSearch

import com.example.recommendtrack.data.dto.searchDto.ExternalUrls
import com.example.recommendtrack.data.dto.searchDto.Image

data class Album(
    val album_type: String,
    val artists: List<ArtistInTrack>,
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val images: List<Image>,
    val is_playable: Boolean,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val type: String,
    val uri: String
)