package com.example.recommendtrack.data.dto.searchDto.trackDto


data class Tracks(
    val href: String,
    val items: List<ItemInTrack>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)