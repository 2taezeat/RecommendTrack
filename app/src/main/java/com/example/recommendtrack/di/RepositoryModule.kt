package com.example.recommendtrack.di

import com.example.recommendtrack.data.repositoryImp.ArtistRepositoryImp
import com.example.recommendtrack.data.repositoryImp.GenreRepositoryImp
import com.example.recommendtrack.data.repositoryImp.TokenRepositoryImp
import com.example.recommendtrack.domain.repository.ArtistRepository
import com.example.recommendtrack.domain.repository.GenreRepository
import com.example.recommendtrack.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


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


    @Binds
    abstract fun bindArtistRepository(
        artistRepositoryImp: ArtistRepositoryImp
    ): ArtistRepository



}