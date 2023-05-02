package com.example.recommendtrack.presentation.ui.artist

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.usecase.artist.SearchArtistUseCase
import com.example.recommendtrack.utils.PreferenceKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val searchArtistUseCase: SearchArtistUseCase,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _searchArtist = MutableLiveData<Artist>()
    val searchArtist: LiveData<Artist> = _searchArtist

    init {

    }

    private fun tokenFlowFromDataStore(): Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferenceKey.tokenPreferenceKey] ?: ""
    }

    fun searchArtist(artistName: String) {
        viewModelScope.launch {
            val accessToken = "Bearer ${tokenFlowFromDataStore().first()}"
            searchArtistUseCase.invoke(accessToken, "artist:${artistName}").collect { it ->
                _searchArtist.value = it
            }
        }
    }

    override fun onCleared() {
        Timber.d("onCleared_genreViewModel")
        super.onCleared()
    }


}