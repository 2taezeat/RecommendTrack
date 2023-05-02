package com.example.recommendtrack.data.database

import androidx.room.*
import com.example.recommendtrack.domain.entity.Genre
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    fun getMyGenres(): Flow<List<Genre>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMyGenres(genres: List<Genre>)

    @Delete
    suspend fun deleteMyGenres(genres: List<Genre>)

}