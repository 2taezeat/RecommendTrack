package com.example.recommendtrack.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genre(
    @PrimaryKey val name: String,
    var isSelected: Boolean = false

)