package com.example.recommendtrack.presentation.song

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.domain.entity.Song
import com.example.recommendtrack.domain.usecase.song.SearchSongUseCase
import com.example.recommendtrack.domain.usecase.token.GetTokenUseCase
import com.example.recommendtrack.presentation.main.TokenViewModel
import com.example.recommendtrack.utils.Constants.TOKEN_TYPE_BEARER
import com.example.recommendtrack.utils.Constants.TRACK_STRING_VALUE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SongViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val searchSongUseCase: SearchSongUseCase,
    private val dataStore: DataStore<Preferences>
) : TokenViewModel(getTokenUseCase, dataStore) {


    private val _searchedSongs = MutableLiveData<List<Song>>()
    val searchedSongs : LiveData<List<Song>> = _searchedSongs


    init {
        searchSong("uprising")
    }


    fun searchSong(songName: String) {
        viewModelScope.launch {
            val accessToken = "$TOKEN_TYPE_BEARER} ${tokenFlowFromDataStore().first()}"
            searchSongUseCase.invoke(accessToken, "${TRACK_STRING_VALUE}:${songName}", onError = { errorMessage = it }, 1, 0).collect { it ->
                _searchedSongs.value = it
            }
        }
    }




}