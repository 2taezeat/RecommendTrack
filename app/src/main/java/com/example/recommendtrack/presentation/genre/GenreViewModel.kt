package com.example.recommendtrack.presentation.ui.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.domain.usecase.GetAllGenreUseCase
import kotlinx.coroutines.launch

class GenreViewModel(private val GetAllGenreUseCase: GetAllGenreUseCase) : ViewModel() {


    private val _genres: MutableLiveData<List<String>> = MutableLiveData()
    val genres: LiveData<List<String>> = _genres


    fun getAllGenres() {
        viewModelScope.launch {
            _genres.value = GetAllGenreUseCase.invoke()
        }
    }


}