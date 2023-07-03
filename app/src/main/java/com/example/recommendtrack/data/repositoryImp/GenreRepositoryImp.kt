package com.example.recommendtrack.data.repositoryImp

import com.example.recommendtrack.data.database.GenreDao
import com.example.recommendtrack.data.datasource.GenreRemoteDataSource
import com.example.recommendtrack.data.mapper.ErrorEnvelopeMapper
import com.example.recommendtrack.data.mapper.GenreMapper
import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.repository.GenreRepository
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

class GenreRepositoryImp
@Inject constructor(private val dataSource: GenreRemoteDataSource, private val ioDispatcher: CoroutineDispatcher, private val genreDao: GenreDao) : GenreRepository {

    override suspend fun fetchGenres(accessToken: String, onError: (String) -> Unit): Flow<List<Genre>> {
        val response = dataSource.fetchGenres(accessToken)

        val genresFlow = flow {
            response.suspendOnSuccess(GenreMapper) {
                val genres = this
                emit(genres)
                Timber.tag("success").d( "$genres")
            }.suspendOnFailure {
                //Timber.tag("fail").d( "${this.message()}")
                onError( this.message() )
            }.suspendOnError(ErrorEnvelopeMapper) {
                val errorMessage = this.message
                Timber.tag("error").d("$errorMessage")
            }
        }.flowOn(ioDispatcher)

        return genresFlow
    }


    override suspend fun addMyGenres(myGenres : List<Genre>) {
        genreDao.addMyGenres(myGenres)
    }

    override suspend fun fetchMyGenres(): Flow<List<Genre>> {
        return genreDao.getMyGenres().flowOn(ioDispatcher)
    }

    override suspend fun deleteMyGenres(deletingGenres : List<Genre>) {
        genreDao.deleteMyGenres(deletingGenres)
    }

}