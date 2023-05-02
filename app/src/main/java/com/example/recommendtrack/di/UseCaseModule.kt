package com.example.recommendtrack.di

import com.example.recommendtrack.domain.repository.GenreRepository
import com.example.recommendtrack.domain.repository.TokenRepository
import com.example.recommendtrack.domain.usecase.*
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

    @Provides
    fun provideGetTokenUseCase(repository: TokenRepository): GetTokenUseCase  {
        return GetTokenUseCase(repository)
    }

    @Provides
    fun provideAddMyGenresUseCase(repository: GenreRepository): AddMyGenresUseCase  {
        return AddMyGenresUseCase(repository)
    }


    @Provides
    fun provideGetMyGenresUseCase(repository: GenreRepository): GetMyGenresUseCase  {
        return GetMyGenresUseCase(repository)
    }


    @Provides
    fun provideUpdateMyGenresUseCase(repository: GenreRepository): UpdateMyGenresUseCase {
        return UpdateMyGenresUseCase(repository)
    }
}