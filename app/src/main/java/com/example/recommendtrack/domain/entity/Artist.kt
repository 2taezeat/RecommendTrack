package com.example.recommendtrack.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Artist(
    val followers: Int,
    val genres : List<String>,
    @PrimaryKey val id: String,
    val imageUrl: String,
    val name: String,
    val popularity: Int,
): Parcelable