package com.example.recommendtrack.data.mapper

import com.example.recommendtrack.data.dto.SongDto
import com.example.recommendtrack.data.dto.searchDto.SearchMeta
import com.example.recommendtrack.domain.entity.Song
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ApiSuccessModelMapper

object SongMapper : ApiSuccessModelMapper<SongDto, Pair<SearchMeta, List<Song>>>  {
    override fun map(apiSuccessResponse: ApiResponse.Success<SongDto>): Pair<SearchMeta, List<Song>> {
        val tracks = apiSuccessResponse.data.tracks
        val items = tracks.items
        return Pair(
            SearchMeta(
            href = tracks.href,
            limit = tracks.limit,
            next = tracks.next,
            offset = tracks.offset,
            previous = tracks.previous,
            total = tracks.total
        ), items.map {
            Song(
                name = it.name,
                artists = it.artists,
                preview_url = it.preview_url,
                album = it.album,
                id = it.id
            )
        })
    }
}