package com.example.recommendtrack.domain.repository

import com.example.recommendtrack.domain.entity.Artist
import kotlinx.coroutines.flow.Flow

interface ArtistRepository {
    suspend fun searchArtist(accessToken: String, artistName: String, onError: (String) -> Unit): Flow<Artist>


    suspend fun fetchMyArtists(): Flow<List<Artist>>

    suspend fun addMyArtist(myArtist: Artist)

    suspend fun deleteMyArtist(deletingArtist: Artist)

    suspend fun deleteAllMyArtists()


    suspend fun addMyArtists(artists: List<Artist>)

}