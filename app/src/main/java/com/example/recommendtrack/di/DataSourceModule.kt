package com.example.recommendtrack.di

import com.example.recommendtrack.data.datasource.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindGenreRemoteDataSource(dataSource: GenreRemoteDataSourceImp): GenreRemoteDataSource


    @Singleton
    @Binds
    abstract fun bindsTokenRemoteDataSource(dataSource: TokenRemoteDataSourceImp): TokenRemoteDataSource



    @Singleton
    @Binds
    abstract fun bindsArtistRemoteDataSource(dataSource: ArtistRemoteDataSourceImp): ArtistRemoteDataSource


    @Singleton
    @Binds
    abstract fun bindsSongRemoteDataSource(dataSource: SongRemoteDataSourceImp): SongRemoteDataSource
}