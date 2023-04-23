package com.example.recommendtrack.data.dto

import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

data class ErrorEnvelope(
    val code: Int,
    val message: String
)



