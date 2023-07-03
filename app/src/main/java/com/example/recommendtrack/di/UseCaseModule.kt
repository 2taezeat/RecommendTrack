package com.example.recommendtrack.di

import com.example.recommendtrack.domain.repository.ArtistRepository
import com.example.recommendtrack.domain.repository.GenreRepository
import com.example.recommendtrack.domain.repository.TokenRepository
import com.example.recommendtrack.domain.usecase.*
import com.example.recommendtrack.domain.usecase.artist.AddMyArtistUseCase
import com.example.recommendtrack.domain.usecase.artist.DeleteMyArtistUseCase
import com.example.recommendtrack.domain.usecase.artist.GetMyArtistsUseCase
import com.example.recommendtrack.domain.usecase.artist.SearchArtistUseCase
import com.example.recommendtrack.domain.usecase.genre.AddMyGenresUseCase
import com.example.recommendtrack.domain.usecase.genre.DeleteMyGenresUseCase
import com.example.recommendtrack.domain.usecase.genre.GetAllGenreUseCase
import com.example.recommendtrack.domain.usecase.genre.GetMyGenresUseCase
import com.example.recommendtrack.domain.usecase.token.GetTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllGenreUseCase(repository: GenreRepository): GetAllGenreUseCase {
        return GetAllGenreUseCase(repository)
    }

    @Provides
    fun provideGetTokenUseCase(repository: TokenRepository): GetTokenUseCase {
        return GetTokenUseCase(repository)
    }

    @Provides
    fun provideAddMyGenresUseCase(repository: GenreRepository): AddMyGenresUseCase {
        return AddMyGenresUseCase(repository)
    }


    @Provides
    fun provideGetMyGenresUseCase(repository: GenreRepository): GetMyGenresUseCase {
        return GetMyGenresUseCase(repository)
    }


    @Provides
    fun provideDeleteMyGenresUseCase(repository: GenreRepository): DeleteMyGenresUseCase {
        return DeleteMyGenresUseCase(repository)
    }


    @Provides
    fun provideSearchArtistUseCase(repository: ArtistRepository): SearchArtistUseCase {
        return SearchArtistUseCase(repository)
    }

    @Provides
    fun provideAddMyArtistUseCase(repository: ArtistRepository): AddMyArtistUseCase {
        return AddMyArtistUseCase(repository)
    }

    @Provides
    fun provideGetMyArtistsUseCase(repository: ArtistRepository): GetMyArtistsUseCase {
        return GetMyArtistsUseCase(repository)
    }


    @Provides
    fun provideDeleteMyArtistUseCase(repository: ArtistRepository): DeleteMyArtistUseCase {
        return DeleteMyArtistUseCase(repository)
    }


}