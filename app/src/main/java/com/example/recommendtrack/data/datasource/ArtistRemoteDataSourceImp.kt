package com.example.recommendtrack.data.datasource

import com.example.recommendtrack.data.dto.ArtistDto
import com.example.recommendtrack.data.remote.ArtistApi
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArtistRemoteDataSourceImp @Inject constructor(private val artistApi: ArtistApi) :
    ArtistRemoteDataSource {

    override suspend fun searchArtist(
        accessToken: String,
        artistName: String
    ): ApiResponse<ArtistDto> {
        return artistApi.getArtist(accessToken = accessToken, artistName = artistName)
    }


}