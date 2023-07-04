package com.example.recommendtrack.data.dto.searchDto.artistSearch


data class Artists(
    val href: String,
    val items: List<ItemInArtist>,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int
)