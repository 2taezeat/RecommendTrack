package com.example.recommendtrack.data.repositoryImp

import com.example.recommendtrack.data.datasource.GenreRemoteDataSource
import com.example.recommendtrack.data.dto.GenresDto
import com.example.recommendtrack.data.mapper.asDomain
import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GenreRepositoryImp
@Inject constructor(private val dataSource: GenreRemoteDataSource) : GenreRepository {
    override suspend fun fetchGenres(): Flow<List<Genre>> {
        return dataSource.fetchGenres().map { genresDto -> genresDto.asDomain() }
    }
}