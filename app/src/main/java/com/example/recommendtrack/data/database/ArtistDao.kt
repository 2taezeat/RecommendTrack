package com.example.recommendtrack.data.database

import androidx.room.*
import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.entity.Genre
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {

    @Query("SELECT * FROM artist")
    fun getMyArtist(): Flow<List<Artist>>


    @Insert
    suspend fun addMyArtist(artist: Artist)

    @Delete
    suspend fun deleteMyArtist(artist: Artist)

}