package com.example.recommendtrack.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendtrack.data.dto.RequestToken
import com.example.recommendtrack.domain.usecase.GetAllGenreUseCase
import com.example.recommendtrack.domain.usecase.GetTokenUseCase
import com.example.recommendtrack.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val getTokenUseCase: GetTokenUseCase) : ViewModel() {


    fun getToken() {
        viewModelScope.launch {
            getTokenUseCase.invoke(requestToken = RequestToken(
                grantType = Constants.SPOTIFY_GRANT_TYPE,
                clientId = Constants.SPOTIFY_CLIENT_ID,
                clientSecret = Constants.SPOTIFY_CLIENT_SECRET
            ))
        }
    }

}