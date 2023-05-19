package com.example.recommendtrack.presentation.genre

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.usecase.genre.AddMyGenresUseCase
import com.example.recommendtrack.domain.usecase.genre.DeleteMyGenresUseCase
import com.example.recommendtrack.domain.usecase.genre.GetAllGenreUseCase
import com.example.recommendtrack.domain.usecase.genre.GetMyGenresUseCase
import com.example.recommendtrack.utils.PreferenceKey.tokenPreferenceKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val getAllGenreUseCase: GetAllGenreUseCase,
    private val addMyGenresUseCase: AddMyGenresUseCase,
    private val getMyGenresUseCase: GetMyGenresUseCase,
    private val deleteMyGenresUseCase: DeleteMyGenresUseCase,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> = _genres

    private val _myGenres = MutableLiveData<List<Genre>>()
    val myGenres: LiveData<List<Genre>> = _myGenres


    init {
        getAllGenres()
        getMyGenres()
    }


    private fun tokenFlowFromDataStore(): Flow<String> = dataStore.data.map { preferences ->
        preferences[tokenPreferenceKey] ?: ""
    }


    fun getAllGenres() {
        viewModelScope.launch {
            val accessToken = "Bearer ${tokenFlowFromDataStore().first()}"
            getAllGenreUseCase.invoke(accessToken).collect { it ->
                Timber.d("${ it }")
                _genres.value = it
            }
        }
    }


    fun addMyGenres(myGenres: List<Genre>) {
        viewModelScope.launch {
            addMyGenresUseCase.invoke(myGenres)
        }
    }

    fun deleteMyGenres(deletingGenres: List<Genre>) {
        viewModelScope.launch {
            deleteMyGenresUseCase.invoke(deletingGenres)
        }
    }


    fun getMyGenres() {
        viewModelScope.launch {
            _myGenres.value = getMyGenresUseCase.invoke().first()
        }
    }



    override fun onCleared() {
        Timber.d("onCleared_genreViewModel")
        super.onCleared()
    }


}