package com.example.recommendtrack.di

import com.example.recommendtrack.data.datasource.GenreRemoteDataSource
import com.example.recommendtrack.data.datasource.GenreRemoteDataSourceImp
import com.example.recommendtrack.data.remote.GenreApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindsGithubRemoteDataSource(dataSource: GenreRemoteDataSourceImp): GenreRemoteDataSource

}