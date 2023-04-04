package com.example.recommendtrack.di

import com.example.recommendtrack.domain.repository.GenreRepository
import com.example.recommendtrack.domain.usecase.GetAllGenreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllGenreUseCase(repository: GenreRepository): GetAllGenreUseCase  {
        return GetAllGenreUseCase(repository)
    }

}