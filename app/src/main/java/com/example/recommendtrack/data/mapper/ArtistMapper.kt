package com.example.recommendtrack.data.mapper

import com.example.recommendtrack.data.dto.ArtistDto
import com.example.recommendtrack.domain.entity.Artist
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ApiSuccessModelMapper

object ArtistMapper : ApiSuccessModelMapper<ArtistDto, Artist> {
    override fun map(apiSuccessResponse: ApiResponse.Success<ArtistDto>): Artist {
        val items = apiSuccessResponse.data.artists.items
        if (items.isEmpty()) {
            return Artist(
                followers = 0,
                genres = listOf(),
                id = "",
                imageUrl = "",
                name = "",
                popularity = 0
            )
        } else {
            val artistItem = items[0]
            return Artist(
                followers = artistItem.followers.total,
                genres = artistItem.genres,
                id = artistItem.id,
                imageUrl = artistItem.images[0].url,
                name = artistItem.name,
                popularity = artistItem.popularity
            )
        }

    }

}