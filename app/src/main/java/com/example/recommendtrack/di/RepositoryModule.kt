package com.example.recommendtrack.di

import com.example.recommendtrack.data.repositoryImp.GenreRepositoryImp
import com.example.recommendtrack.domain.repository.GenreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGenreRepository(
        genreRepositoryImp: GenreRepositoryImp
    ): GenreRepository
}