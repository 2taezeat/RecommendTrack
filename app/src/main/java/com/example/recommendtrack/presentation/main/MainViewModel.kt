package com.example.recommendtrack.presentation.main

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.domain.usecase.GetTokenUseCase
import com.example.recommendtrack.utils.Constants.SPOTIFY_CLIENT_ID
import com.example.recommendtrack.utils.Constants.SPOTIFY_CLIENT_SECRET
import com.example.recommendtrack.utils.Constants.SPOTIFY_GRANT_TYPE
import com.example.recommendtrack.utils.PreferenceKey.tokenPreferenceKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {


    init {
        getToken()
    }

    private fun tokenFlowFromDataStore(): Flow<String> = dataStore.data.map { preferences ->
        preferences[tokenPreferenceKey] ?: ""
    }

    private suspend fun writeTokenDataStore(str: String) {
        dataStore.edit { preferences ->
            preferences[tokenPreferenceKey] = str
        }
    }

    private fun getToken() {
        viewModelScope.launch {
            val tokenString = tokenFlowFromDataStore().first()
            if (tokenString.isEmpty()) {
                val tokenRemoteFlow = getTokenUseCase.invoke(
                    SPOTIFY_GRANT_TYPE,
                    SPOTIFY_CLIENT_ID,
                    SPOTIFY_CLIENT_SECRET
                )
                tokenRemoteFlow.collect { token ->
                    writeTokenDataStore(token.accessToken)
                }
            }
        }
    }






}