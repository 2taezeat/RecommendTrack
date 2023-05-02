package com.example.recommendtrack.domain.repository

import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.entity.Genre
import kotlinx.coroutines.flow.Flow

interface ArtistRepository {
    suspend fun fetchArtist(accessToken: String, artistName: String): Flow<Artist>


    suspend fun fetchMyArtist(): Flow<List<Artist>>

    suspend fun addMyArtist(myArtist : Artist)

    suspend fun deleteMyArtist(deletingArtist : Artist)

}