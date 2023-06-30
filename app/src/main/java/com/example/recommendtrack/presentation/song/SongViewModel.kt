package com.example.recommendtrack.presentation.song

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.recommendtrack.domain.usecase.GetTokenUseCase
import com.example.recommendtrack.domain.usecase.artist.AddMyArtistUseCase
import com.example.recommendtrack.domain.usecase.artist.DeleteMyArtistUseCase
import com.example.recommendtrack.domain.usecase.artist.GetMyArtistsUseCase
import com.example.recommendtrack.domain.usecase.artist.SearchArtistUseCase
import com.example.recommendtrack.presentation.main.TokenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SongViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val searchArtistUseCase: SearchArtistUseCase,
    private val getMyArtistsUseCase: GetMyArtistsUseCase,
    private val addMyArtistUseCase: AddMyArtistUseCase,
    private val deleteMyArtistUseCase: DeleteMyArtistUseCase,
    private val dataStore: DataStore<Preferences>
) : TokenViewModel(getTokenUseCase, dataStore) {





}