package com.example.recommendtrack.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recommendtrack.domain.entity.Artist


@Database(entities = [Artist::class], version = 1)
@TypeConverters(ArtistTypeConverters::class)
abstract class ArtistDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
}

