package com.example.recommendtrack.presentation.artist

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.usecase.artist.AddMyArtistUseCase
import com.example.recommendtrack.domain.usecase.artist.DeleteMyArtistUseCase
import com.example.recommendtrack.domain.usecase.artist.GetMyArtistsUseCase
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
    private val getMyArtistsUseCase: GetMyArtistsUseCase,
    private val addMyArtistUseCase: AddMyArtistUseCase,
    private val deleteMyArtistUseCase: DeleteMyArtistUseCase,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _searchArtist = MutableLiveData<Artist>()
    val searchArtist: LiveData<Artist> = _searchArtist

    private val _myArtists = MutableLiveData<List<Artist>>()
    val myArtists: LiveData<List<Artist>> = _myArtists

    init {
        getMyArtists()
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


    fun addMyArtist(myArtist: Artist) {
        viewModelScope.launch {
            addMyArtistUseCase.invoke(myArtist)
        }
    }

    fun deleteMyArtist(deleteArtist: Artist) {
        viewModelScope.launch {
            deleteMyArtistUseCase.invoke(deleteArtist)
        }
    }


    fun getMyArtists() {
        viewModelScope.launch {
            _myArtists.value = getMyArtistsUseCase.invoke().first()
            Timber.d("${_myArtists.value}")
        }
    }




    override fun onCleared() {
        Timber.d("onCleared_genreViewModel")
        super.onCleared()
    }


}