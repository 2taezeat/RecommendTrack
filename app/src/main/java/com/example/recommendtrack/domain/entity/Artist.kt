package com.example.recommendtrack.domain.entity

data class Artist(
    val followers: Int,
    val genres : List<String>,
    val id: String,
    val imageUrl: String,
    val name: String,
    val popularity: Int,
)