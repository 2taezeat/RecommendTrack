package com.example.recommendtrack.data.database

import androidx.room.*
import com.example.recommendtrack.domain.entity.Artist
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {

    @Query("SELECT * FROM artist")
    fun getMyArtists(): Flow<List<Artist>>


    @Insert
    suspend fun addMyArtist(artist: Artist)

    @Delete
    suspend fun deleteMyArtist(artist: Artist)


    @Insert
    suspend fun addMyArtists(artists: List<Artist>)


    @Query("DELETE FROM artist")
    suspend fun deleteAllMyArtists()

}