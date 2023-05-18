package com.example.recommendtrack.di

import android.content.Context
import androidx.room.Room
import com.example.recommendtrack.data.database.ArtistDao
import com.example.recommendtrack.data.database.ArtistDatabase
import com.example.recommendtrack.data.database.GenreDao
import com.example.recommendtrack.data.database.GenreDatabase
import com.example.recommendtrack.utils.Constants.ARTIST_DATABASE_NAME
import com.example.recommendtrack.utils.Constants.GENRE_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideGenreDataBase(@ApplicationContext context: Context): GenreDatabase {
        return Room.databaseBuilder(
            context,
            GenreDatabase::class.java,
            GENRE_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideGenreDao(db: GenreDatabase): GenreDao {
        return db.genreDao()
    }


    @Provides
    @Singleton
    fun provideArtistDataBase(@ApplicationContext context: Context): ArtistDatabase {
        return Room.databaseBuilder(
            context,
            ArtistDatabase::class.java,
            ARTIST_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideArtistDao(db: ArtistDatabase): ArtistDao {
        return db.artistDao()
    }

}