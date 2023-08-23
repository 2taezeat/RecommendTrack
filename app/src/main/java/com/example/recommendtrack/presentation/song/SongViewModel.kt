package com.example.recommendtrack.presentation.song

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.recommendtrack.domain.entity.Song
import com.example.recommendtrack.domain.usecase.song.SearchSongUseCase
import com.example.recommendtrack.domain.usecase.token.GetTokenUseCase
import com.example.recommendtrack.presentation.main.TokenViewModel
import com.example.recommendtrack.utils.Constants.TOKEN_TYPE_BEARER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SongViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val searchSongUseCase: SearchSongUseCase,
    private val dataStore: DataStore<Preferences>
) : TokenViewModel(getTokenUseCase, dataStore) {

    private val _searchedPagedSongs = MutableStateFlow<PagingData<Song>>(PagingData.empty())
    val searchedPagedSongs: StateFlow<PagingData<Song>> = _searchedPagedSongs.asStateFlow()


    init {

    }



    fun searchSongsPaging(songName: String) {
        viewModelScope.launch {
            val accessToken = "$TOKEN_TYPE_BEARER ${tokenFlowFromDataStore().first()}"
            searchSongUseCase(
                accessToken = accessToken,
                songName = songName,
                onError = { errorMessage = it }
            ).cachedIn(viewModelScope).collect { _searchedPagedSongs.value = it }
        }
    }



}