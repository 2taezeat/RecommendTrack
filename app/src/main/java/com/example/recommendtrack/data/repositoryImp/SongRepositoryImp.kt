package com.example.recommendtrack.data.repositoryImp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.recommendtrack.data.PagingRepository.SongPagingRepository
import com.example.recommendtrack.data.datasource.SongRemoteDataSource
import com.example.recommendtrack.domain.entity.Song
import com.example.recommendtrack.domain.repository.SongRepository
import com.example.recommendtrack.utils.Constants.PAGING_SIZE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class SongRepositoryImp @Inject constructor(
    private val dataSource: SongRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : SongRepository {

    override suspend fun searchSongsPaging(
        accessToken: String,
        songName: String,
        onError: (String) -> Unit,
    ): Flow<PagingData<Song>> {
        Timber.d("searchSongsPaging")
        val pagingSourceFactory = {
            SongPagingRepository(
                dataSource = dataSource,
                accessToken = accessToken,
                songName = songName,
                onError = onError
            )
        }

        return Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = false,
                maxSize = PAGING_SIZE * 3
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }


}






