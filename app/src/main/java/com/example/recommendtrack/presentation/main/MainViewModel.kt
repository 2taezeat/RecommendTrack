package com.example.recommendtrack.presentation.main

import androidx.lifecycle.ViewModel
import com.example.recommendtrack.domain.usecase.GetAllGenreUseCase
import com.example.recommendtrack.domain.usecase.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val getTokenUseCase: GetTokenUseCase) : ViewModel() {



}