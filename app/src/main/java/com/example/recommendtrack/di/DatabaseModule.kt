package com.example.recommendtrack.di

import android.content.Context
import androidx.room.Room
import com.example.recommendtrack.data.database.GenreDao
import com.example.recommendtrack.data.database.GenreDatabase
import com.example.recommendtrack.utils.Constants.GENRE_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import javax.inject.Qualifier



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

}