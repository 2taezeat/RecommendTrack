package com.example.recommendtrack.data.repositoryImp

import com.example.recommendtrack.data.datasource.SongRemoteDataSource
import com.example.recommendtrack.data.mapper.ErrorEnvelopeMapper
import com.example.recommendtrack.data.mapper.SongMapper
import com.example.recommendtrack.domain.entity.Song
import com.example.recommendtrack.domain.repository.SongRepository
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class SongRepositoryImp @Inject constructor(
    private val dataSource: SongRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : SongRepository {

    override suspend fun searchSong(
        accessToken: String,
        songName: String,
        onError: (String) -> Unit,
        limit: Int,
        offset: Int
    ): Flow<List<Song>> {
        val response = dataSource.searchSong(accessToken, songName, limit, offset)

        val songFlow = flow {
            response.suspendOnSuccess(SongMapper) {
                val song = this
                emit(song)
                Timber.tag("success").d("$song")
            }.suspendOnFailure {
                //Timber.tag("fail").d("${this.message()}")
                onError(this.message())
            }.suspendOnError(ErrorEnvelopeMapper) {
                val errorMessage = this.message
                Timber.tag("error").d("$errorMessage")
            }
        }.flowOn(ioDispatcher)


        return songFlow
    }
}






