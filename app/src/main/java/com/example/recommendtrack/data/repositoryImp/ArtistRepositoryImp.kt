package com.example.recommendtrack.data.repositoryImp

import com.example.recommendtrack.data.database.ArtistDao
import com.example.recommendtrack.data.datasource.ArtistRemoteDataSource
import com.example.recommendtrack.data.mapper.ArtistMapper
import com.example.recommendtrack.data.mapper.ErrorEnvelopeMapper
import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.repository.ArtistRepository
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

class ArtistRepositoryImp
@Inject constructor(private val dataSource: ArtistRemoteDataSource, private val ioDispatcher: CoroutineDispatcher, private val artistDao: ArtistDao) : ArtistRepository {

    override suspend fun fetchArtist(accessToken: String, artistName: String): Flow<Artist> {
        val response = dataSource.fetchArtist(accessToken, artistName)

        val flowArtist = flow {
            response.suspendOnSuccess(ArtistMapper) {
                val artist = this
                emit(artist)
                Timber.tag("success").d("$artist")
            }.suspendOnFailure {
                Timber.tag("fail").d("${this.message()}")
            }.suspendOnError(ErrorEnvelopeMapper) {
                val errorMessage = this.message
                Timber.tag("error").d("$errorMessage")
            }
        }.flowOn(ioDispatcher)


        return flowArtist
    }

    override suspend fun fetchMyArtist(): Flow<List<Artist>> {
        return artistDao.getMyArtist().flowOn(ioDispatcher)
    }

    override suspend fun addMyArtist(myArtist: Artist) {
        artistDao.addMyArtist(myArtist)
    }

    override suspend fun deleteMyArtist(deletingArtist: Artist) {
        artistDao.deleteMyArtist(deletingArtist)
    }
}