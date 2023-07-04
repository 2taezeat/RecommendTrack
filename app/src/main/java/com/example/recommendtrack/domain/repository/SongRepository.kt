package com.example.recommendtrack.domain.repository

import androidx.paging.PagingData
import com.example.recommendtrack.domain.entity.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    suspend fun searchSongsPaging(accessToken: String, songName: String, onError: (String) -> Unit): Flow<PagingData<Song>>

}