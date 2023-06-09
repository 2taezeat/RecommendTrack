package com.example.recommendtrack.domain.entity

import androidx.room.Entity
import com.example.recommendtrack.data.dto.searchDto.trackSearch.Album
import com.example.recommendtrack.data.dto.searchDto.trackSearch.ArtistInTrack


@Entity
data class Song(
    val name: String,
    val artists : List<ArtistInTrack>,
    val preview_url: String?,
    val album: Album,
    val id: String
)
