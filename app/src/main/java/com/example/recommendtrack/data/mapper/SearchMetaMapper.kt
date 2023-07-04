package com.example.recommendtrack.data.mapper

import com.example.recommendtrack.data.dto.SongDto
import com.example.recommendtrack.data.dto.searchDto.SearchResponseMeta
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ApiSuccessModelMapper

object SearchMetaMapper: ApiSuccessModelMapper<SongDto, SearchResponseMeta> {
    override fun map(apiSuccessResponse: ApiResponse.Success<SongDto>): SearchResponseMeta {
        val tracks = apiSuccessResponse.data.tracks
        return SearchResponseMeta(
            href = tracks.href,
            limit = tracks.limit,
            next = tracks.next,
            offset = tracks.offset,
            previous = tracks.previous,
            total = tracks.total
        )
    }


}


