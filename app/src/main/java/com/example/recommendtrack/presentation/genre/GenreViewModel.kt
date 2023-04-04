package com.example.recommendtrack.presentation.ui.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.domain.usecase.GetAllGenreUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GenreViewModel(private val GetAllGenreUseCase: GetAllGenreUseCase) : ViewModel() {


    private val _genres = MutableStateFlow<List<String>>(emptyList())
    val genres = _genres.asStateFlow()


    fun getAllGenres() {
//        viewModelScope.launch {
//            _genres.value = GetAllGenreUseCase.invoke()
//        }
    }


}