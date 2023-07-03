package com.example.recommendtrack.presentation.artist

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.domain.usecase.artist.AddMyArtistUseCase
import com.example.recommendtrack.domain.usecase.artist.DeleteMyArtistUseCase
import com.example.recommendtrack.domain.usecase.artist.GetMyArtistsUseCase
import com.example.recommendtrack.domain.usecase.artist.SearchArtistUseCase
import com.example.recommendtrack.domain.usecase.token.GetTokenUseCase
import com.example.recommendtrack.presentation.main.TokenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val searchArtistUseCase: SearchArtistUseCase,
    private val getMyArtistsUseCase: GetMyArtistsUseCase,
    private val addMyArtistUseCase: AddMyArtistUseCase,
    private val deleteMyArtistUseCase: DeleteMyArtistUseCase,
    private val dataStore: DataStore<Preferences>
) : TokenViewModel(getTokenUseCase, dataStore) {

    private val _searchArtist = MutableLiveData<Artist>()
    val searchArtist: LiveData<Artist> = _searchArtist

    private val _myArtists = MutableLiveData<List<Artist>>()
    val myArtists: LiveData<List<Artist>> = _myArtists

    init {
        //getMyArtists()
    }



    fun searchArtist(artistName: String) {
        viewModelScope.launch {
            val accessToken = "Bearer ${tokenFlowFromDataStore().first()}"
            searchArtistUseCase.invoke(accessToken, "artist:${artistName}", onError = { errorMessage = it }).collect { it ->
                _searchArtist.value = it
            }
        }
    }


    fun addMyOneArtist(myArtist: Artist) {
        viewModelScope.launch {
            addMyArtistUseCase.addOneArtist(myArtist)
        }
    }

    fun deleteMyArtist(deleteArtist: Artist) {
        viewModelScope.launch {
            deleteMyArtistUseCase.deleteOneArtist(deleteArtist)
        }
    }


    fun getMyArtists() {
        Timber.d("getMyArtists")
        viewModelScope.launch {
            _myArtists.value = getMyArtistsUseCase.invoke().first()
            Timber.d("${_myArtists.value}")
        }
    }

    private suspend fun deleteAllMyArtists() {
        Timber.d("deleteAllMyArtists")
        deleteMyArtistUseCase.deleteAllArtists()
    }

    private suspend fun addMyArtists(myArtists: List<Artist>) {

        Timber.d("addMyArtists")
        addMyArtistUseCase.addArtists(myArtists)
    }


    fun updateMyArtists(myArtists: List<Artist>) {
        Timber.d("${myArtists }")
        viewModelScope.launch {
            deleteAllMyArtists()
            addMyArtists(myArtists)
            getMyArtists()
        }
    }



    override fun onCleared() {
        Timber.d("onCleared_genreViewModel")
        super.onCleared()
    }


}