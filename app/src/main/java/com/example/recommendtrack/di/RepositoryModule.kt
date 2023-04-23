package com.example.recommendtrack.di

import com.example.recommendtrack.data.datasource.GenreRemoteDataSource
import com.example.recommendtrack.data.datasource.TokenRemoteDataSource
import com.example.recommendtrack.data.repositoryImp.GenreRepositoryImp
import com.example.recommendtrack.data.repositoryImp.TokenRepositoryImp
import com.example.recommendtrack.domain.repository.GenreRepository
import com.example.recommendtrack.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGenreRepository(
        genreRepositoryImp: GenreRepositoryImp
    ): GenreRepository


    @Binds
    abstract fun bindTokenRepository(
        tokenRepositoryImp: TokenRepositoryImp
    ): TokenRepository
}