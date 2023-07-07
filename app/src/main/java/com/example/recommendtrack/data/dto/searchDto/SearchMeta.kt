package com.example.recommendtrack.data.dto.searchDto

data class SearchMeta(
    val href: String,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int
)
