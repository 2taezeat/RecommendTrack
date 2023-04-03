package com.example.recommendtrack.domain.repository

interface GenreRepository {
    suspend fun fetchGenres(): List<String>
}