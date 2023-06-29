package com.example.recommendtrack.presentation.main

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.recommendtrack.domain.usecase.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val dataStore: DataStore<Preferences>
) : TokenViewModel(getTokenUseCase, dataStore) {


    init {
        //getToken()
    }




}