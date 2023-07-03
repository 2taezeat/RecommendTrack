package com.example.recommendtrack.domain.usecase.song

import com.example.recommendtrack.domain.entity.Song
import com.example.recommendtrack.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow

class SearchSongUseCase( private val songRepository: SongRepository) {
    suspend fun invoke(accessToken: String, songName: String, onError: (String) -> Unit, limit: Int, offset: Int) : Flow<List<Song>> {
        return songRepository.searchSong(accessToken, songName, onError, limit, offset)
    }


}


