package com.example.recommendtrack.data.mapper

import com.example.recommendtrack.data.dto.GenresDto
import com.example.recommendtrack.data.dto.TokenDto
import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.entity.Token


internal fun GenresDto.asDomain(): List<Genre> {
    return this.genres.toList().map { Genre(it) }
}


internal fun TokenDto.asDomain(): Token {
    return Token(this.accessToken)
}