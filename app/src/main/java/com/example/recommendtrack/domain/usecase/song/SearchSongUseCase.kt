package com.example.recommendtrack.domain.usecase.song

import androidx.paging.PagingData
import com.example.recommendtrack.domain.entity.Song
import com.example.recommendtrack.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow

class SearchSongUseCase( private val songRepository: SongRepository) {
    suspend operator fun invoke(accessToken: String, songName: String, onError: (String) -> Unit) : Flow<PagingData<Song>> {
        return songRepository.searchSongsPaging(accessToken, songName, onError)
    }


}


