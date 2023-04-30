package com.example.recommendtrack.data.mapper

import com.example.recommendtrack.data.dto.GenresDto
import com.example.recommendtrack.data.dto.TokenDto
import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.entity.Token
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ApiSuccessModelMapper




object GenreMapper : ApiSuccessModelMapper<GenresDto, List<Genre>> {
    override fun map(apiSuccessResponse: ApiResponse.Success<GenresDto>): List<Genre> {
        return apiSuccessResponse.data.genres.map { Genre( name = it)  }
    }

}