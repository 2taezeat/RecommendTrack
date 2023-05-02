package com.example.recommendtrack.data.database

import androidx.room.*
import com.example.recommendtrack.domain.entity.Genre
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre WHERE isSelected = true")
    fun getMyGenres(): Flow<List<Genre>>


    @Transaction
    open suspend fun updateMyGenre(genres: List<Genre>) {
        addMyGenres(genres)
        deleteMyGenres(genres)
    }


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMyGenres(genres: List<Genre>)

    @Delete
    suspend fun deleteMyGenres(genres: List<Genre>)

}