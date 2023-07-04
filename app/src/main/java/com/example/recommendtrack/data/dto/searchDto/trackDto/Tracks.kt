package com.example.recommendtrack.data.dto.searchDto.trackDto


data class Tracks(
    val items: List<ItemInTrack>,
    val href: String,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int
)