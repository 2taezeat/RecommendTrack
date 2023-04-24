package com.example.recommendtrack.presentation.main

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.domain.usecase.GetTokenUseCase
import com.example.recommendtrack.utils.Constants
import com.example.recommendtrack.utils.Constants.ACCESS_TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val tokenPreferenceKey = stringPreferencesKey(ACCESS_TOKEN)

    init {
        checkToken()
    }


    private fun checkToken() {
        val tokenFlow =
            dataStore.data.map { preferences ->
                preferences[tokenPreferenceKey] ?: ""
            }

        viewModelScope.launch {
            tokenFlow.collect { it ->
                Log.d("collect", "$it")

            }
        }
    }


    fun getToken() {
        viewModelScope.launch {
            getTokenUseCase.invoke(
                grantType = Constants.SPOTIFY_GRANT_TYPE,
                clientId = Constants.SPOTIFY_CLIENT_ID,
                clientSecret = Constants.SPOTIFY_CLIENT_SECRET
            ).collect { token ->
                Log.d("viewModel_getToken", "$token")
                val accessToken = token.accessToken

                dataStore.edit { preferences ->
                    preferences[tokenPreferenceKey] = accessToken
                }

            }
        }
    }

}