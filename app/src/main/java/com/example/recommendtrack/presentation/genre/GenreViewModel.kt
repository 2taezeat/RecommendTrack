package com.example.recommendtrack.presentation.ui.artist

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.*
import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.domain.usecase.UpdateMyGenresUseCase
import com.example.recommendtrack.domain.usecase.GetAllGenreUseCase
import com.example.recommendtrack.domain.usecase.GetMyGenresUseCase
import com.example.recommendtrack.domain.usecase.SaveMyGenresUseCase
import com.example.recommendtrack.utils.PreferenceKey.tokenPreferenceKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val getAllGenreUseCase: GetAllGenreUseCase,
    private val saveMyGenresUseCase: SaveMyGenresUseCase,
    private val getMyGenresUseCase: GetMyGenresUseCase,
    private val deleteMyGenresUseCase: UpdateMyGenresUseCase,
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
                _genres.value = it
            }

        }
    }


    fun saveMyGenres(myGenres: List<Genre>) {
        viewModelScope.launch {
            saveMyGenresUseCase.invoke(myGenres)
        }
    }

    fun getMyGenres() {
        viewModelScope.launch {
            _myGenres.value = getMyGenresUseCase.invoke().first()
        }
    }

    fun updateMyGenres(myGenres: List<Genre>) {
        viewModelScope.launch {

        }
    }

    override fun onCleared() {
        Timber.d("onCleared_genreViewModel")
        super.onCleared()
    }


}