package com.example.recommendtrack.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ContentInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRetrofit


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ContentRetrofit