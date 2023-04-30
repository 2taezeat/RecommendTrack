package com.example.recommendtrack.presentation.ui.artist

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.*
import com.example.recommendtrack.domain.usecase.GetAllGenreUseCase
import com.example.recommendtrack.utils.PreferenceKey.tokenPreferenceKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val getAllGenreUseCase: GetAllGenreUseCase, private val dataStore: DataStore<Preferences>) : ViewModel() {


    init {

    }

    private val _genres = MutableStateFlow<List<String>>(emptyList())
    val genres = _genres.asStateFlow()


    private fun tokenFlowFromDataStore(): Flow<String> = dataStore.data.map { preferences ->
        preferences[tokenPreferenceKey] ?: ""
    }



    fun getAllGenres() {
        Log.d("getAllGenres", "init")
        viewModelScope.launch {
            val accessToken = "Bearer ${tokenFlowFromDataStore().first()}"
            val genresLiveData = getAllGenreUseCase.invoke(accessToken).asLiveData()
            Log.d("getAllGenres", "${genresLiveData}")
        }
    }


}