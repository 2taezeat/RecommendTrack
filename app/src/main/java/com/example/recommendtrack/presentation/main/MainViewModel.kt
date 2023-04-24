package com.example.recommendtrack.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.domain.usecase.GetTokenUseCase
import com.example.recommendtrack.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val getTokenUseCase: GetTokenUseCase) : ViewModel() {


    fun getToken() {
        viewModelScope.launch {
            getTokenUseCase.invoke(
                grantType = Constants.SPOTIFY_GRANT_TYPE,
                clientId = Constants.SPOTIFY_CLIENT_ID,
                clientSecret = Constants.SPOTIFY_CLIENT_SECRET
            ).collect { token ->
                Log.d("viewModel_getToken", "$token")
                val accessToken = token.accessToken

            }
        }
    }

}