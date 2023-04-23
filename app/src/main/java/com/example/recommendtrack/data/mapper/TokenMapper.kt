package com.example.recommendtrack.data.mapper

import com.example.recommendtrack.data.dto.TokenDto
import com.example.recommendtrack.domain.entity.Token
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ApiSuccessModelMapper

object TokenMapper : ApiSuccessModelMapper<TokenDto, Token> {
    override fun map(apiSuccessResponse: ApiResponse.Success<TokenDto>): Token {
        return Token(apiSuccessResponse.data.accessToken)
    }

}