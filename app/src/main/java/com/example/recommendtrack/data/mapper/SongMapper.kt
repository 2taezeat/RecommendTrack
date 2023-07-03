package com.example.recommendtrack.data.mapper

import com.example.recommendtrack.data.dto.SongDto
import com.example.recommendtrack.domain.entity.Song
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ApiSuccessModelMapper

object SongMapper : ApiSuccessModelMapper<SongDto, List<Song>> {
    override fun map(apiSuccessResponse: ApiResponse.Success<SongDto>): List<Song> {
        val items = apiSuccessResponse.data.tracks.items
        return items.map { Song(name = it.name, artists = it.artists, preview_url = it.preview_url) }
    }

}