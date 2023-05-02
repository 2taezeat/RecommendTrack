package com.example.recommendtrack.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Artist(
    val followers: Int,
    val genres : List<String>,
    @PrimaryKey val id: String,
    val imageUrl: String,
    val name: String,
    val popularity: Int,
)