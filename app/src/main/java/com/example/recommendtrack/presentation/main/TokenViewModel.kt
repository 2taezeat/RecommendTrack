package com.example.recommendtrack.presentation.main

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.BuildConfig
import com.example.recommendtrack.domain.usecase.token.GetTokenUseCase
import com.example.recommendtrack.utils.Constants
import com.example.recommendtrack.utils.Event
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

    private val _refreshToken = MutableLiveData<Event<String>>()
    val refreshToken: LiveData<Event<String>> = _refreshToken

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
            _refreshToken.value = Event(tokenString)
        }
    }


    fun refreshToken() {
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