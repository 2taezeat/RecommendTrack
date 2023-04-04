package com.example.recommendtrack.data.mapper

import com.example.recommendtrack.data.dto.GenresDto
import com.example.recommendtrack.domain.entity.Genre


internal fun GenresDto.asDomain(): List<Genre> {
    return this.genres.toList().map { Genre(it) }
}
