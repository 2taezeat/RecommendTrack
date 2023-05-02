package com.example.recommendtrack.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recommendtrack.domain.entity.Artist


@Database(entities = [Artist::class], version = 1)
abstract class ArtistDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
}