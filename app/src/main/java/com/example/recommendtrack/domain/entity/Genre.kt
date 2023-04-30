package com.example.recommendtrack.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Genre(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val name: String,
    var isSelected: Boolean = false

)