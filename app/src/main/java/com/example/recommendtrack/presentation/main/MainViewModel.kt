package com.example.recommendtrack.presentation.main

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.BuildConfig
import com.example.recommendtrack.domain.usecase.GetTokenUseCase
import com.example.recommendtrack.utils.Constants.SPOTIFY_GRANT_TYPE
import com.example.recommendtrack.utils.PreferenceKey.tokenPreferenceKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
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
                    BuildConfig.SPOTIFY_CLIENT_ID,
                    BuildConfig.SPOTIFY_CLIENT_SECRET
                )
                tokenRemoteFlow.collect { token ->
                    writeTokenDataStore(token.accessToken)
                }
            }
        }
    }


    fun refreshToken() {
        viewModelScope.launch {
            removeToken()
            getToken()
        }
    }

    private suspend fun removeToken() {
        writeTokenDataStore("")
        Timber.d("removeToken")
    }





}