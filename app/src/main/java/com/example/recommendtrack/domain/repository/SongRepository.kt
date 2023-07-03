package com.example.recommendtrack.domain.repository

import com.example.recommendtrack.domain.entity.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    suspend fun searchSong(accessToken: String, songName: String, onError: (String) -> Unit, limit: Int, offset: Int): Flow<List<Song>>


}