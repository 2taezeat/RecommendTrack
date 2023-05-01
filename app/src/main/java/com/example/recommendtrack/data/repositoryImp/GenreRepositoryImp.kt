package com.example.recommendtrack.data.repositoryImp

import android.util.Log
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
import javax.inject.Inject

class GenreRepositoryImp
@Inject constructor(private val dataSource: GenreRemoteDataSource, private val ioDispatcher: CoroutineDispatcher, private val genreDao: GenreDao) : GenreRepository {

    override suspend fun fetchGenres(accessToken: String): Flow<List<Genre>> {
        val response = dataSource.fetchGenres(accessToken)

        val flowGenres = flow {
            response.suspendOnSuccess(GenreMapper) {
                val genres = this
                emit(genres)
                Log.d("success", "$genres")
            }.suspendOnFailure {
                Log.d("fail", "${this.message()}")
            }.suspendOnError(ErrorEnvelopeMapper) {
                val errorMessage = this.message
                Log.d("error", "$errorMessage")
            }
        }.flowOn(ioDispatcher)

        return flowGenres
    }


    override suspend fun addMyGenres(myGenres : List<Genre>) {
        genreDao.addMyGenres(myGenres)
    }

    override suspend fun fetchMyGenres(): Flow<List<Genre>> {
        return genreDao.getMyGenres().flowOn(ioDispatcher)
    }
}