package com.example.recommendtrack.presentation.ui.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.domain.usecase.GetAllGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val getAllGenreUseCase: GetAllGenreUseCase) : ViewModel() {


    private val _genres = MutableStateFlow<List<String>>(emptyList())
    val genres = _genres.asStateFlow()


    fun getAllGenres() {
        viewModelScope.launch {
            //_genres.value = getAllGenreUseCase.invoke()
        }
    }


}