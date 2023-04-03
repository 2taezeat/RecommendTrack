package com.example.recommendtrack.data.remote

import com.example.recommendtrack.data.dto.Genre
import okhttp3.Call
import retrofit2.http.GET

interface GenreApi {

    @GET("recommendations/available-genre-seeds")
    fun getGenres(): retrofit2.Call<Genre>

}