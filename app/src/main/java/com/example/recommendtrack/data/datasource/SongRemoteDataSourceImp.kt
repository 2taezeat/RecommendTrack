package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.SongDto
import com.example.recommendtrack.data.remote.SongApi
import com.skydoves.sandwich.ApiResponse
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongRemoteDataSourceImp @Inject constructor(private val songApi: SongApi) :
    SongRemoteDataSource {

    override suspend fun searchSongs(
        accessToken: String, songName: String, limit: Int, offset: Int
    ): ApiResponse<SongDto> {
        Timber.d("searchSongs, ${offset}")
        return songApi.getSongs(accessToken = accessToken, songName = songName, limit = limit, offset = offset)
    }


}