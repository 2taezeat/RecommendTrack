package com.example.recommendtrack.presentation.main

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.BuildConfig
import com.example.recommendtrack.domain.usecase.token.GetTokenUseCase
import com.example.recommendtrack.utils.Constants
import com.example.recommendtrack.utils.PreferenceKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber


abstract class TokenViewModel(
    private val getTokenUseCase: GetTokenUseCase,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {


    init {
        Timber.d("TokenViewModel.init")
    }

    var errorMessage : String = ""
        set(value) {
            refreshToken()
            field = value
        }

    fun tokenFlowFromDataStore(): Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferenceKey.tokenPreferenceKey] ?: ""
    }



    private suspend fun writeTokenDataStore(str: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.tokenPreferenceKey] = str
        }
    }

    private fun getToken() {
        Timber.d("getToken_Call")
        viewModelScope.launch {
            val tokenString = tokenFlowFromDataStore().first()
            if (tokenString.isEmpty()) {
                val tokenRemoteFlow = getTokenUseCase(
                    Constants.SPOTIFY_GRANT_TYPE,
                    BuildConfig.SPOTIFY_CLIENT_ID,
                    BuildConfig.SPOTIFY_CLIENT_SECRET
                )
                tokenRemoteFlow.collect { token ->
                    writeTokenDataStore(token.accessToken)
                }
            }
        }
    }


    private fun refreshToken() {
        Timber.d("refreshToken_Call")
        viewModelScope.launch {
            removeToken()
            getToken()
        }
    }

    fun removeToken() {
        Timber.d("removeToken_call")
        viewModelScope.launch {
            writeTokenDataStore("")
        }
    }


}