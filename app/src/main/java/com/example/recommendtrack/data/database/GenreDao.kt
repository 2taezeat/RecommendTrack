package com.example.recommendtrack.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.recommendtrack.domain.entity.Genre
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    fun getMyGenres(): Flow<List<Genre>>

    @Update
    fun updateMyGenre(genre: Genre)

    @Insert
    fun addMyGenres(genres: List<Genre>)


}