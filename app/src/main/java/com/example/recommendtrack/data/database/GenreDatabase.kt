package com.example.recommendtrack.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recommendtrack.domain.entity.Genre


@Database(entities = [Genre::class], version = 1)
abstract class GenreDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
}