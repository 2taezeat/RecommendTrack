package com.example.recommendtrack.di

import com.example.recommendtrack.BuildConfig
import com.example.recommendtrack.data.remote.ArtistApi
import com.example.recommendtrack.data.remote.GenreApi
import com.example.recommendtrack.data.remote.SongApi
import com.example.recommendtrack.data.remote.TokenApi
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @ContentInterceptorOkHttpClient
    @Provides
    fun provideContentOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAccountOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
                val newRequest = request().newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build()
                return proceed(newRequest)
            }
        }

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor = interceptor)
            .build()
    }



    @Singleton
    @ContentRetrofit
    @Provides
    fun provideBaseUrlRetrofit(@ContentInterceptorOkHttpClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }


    @Singleton
    @AuthRetrofit
    @Provides
    fun provideAccountUrlRetrofit(@AuthInterceptorOkHttpClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BuildConfig.SPOTIFY_ACCOUNT_URL)
            .build()
    }


    @Singleton
    @Provides
    fun provideGenreApi(@ContentRetrofit retrofit: Retrofit): GenreApi {
        return retrofit.create(GenreApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTokenApi(@AuthRetrofit retrofit: Retrofit): TokenApi {
        return retrofit.create(TokenApi::class.java)
    }


    @Singleton
    @Provides
    fun provideArtistApi(@ContentRetrofit retrofit: Retrofit): ArtistApi {
        return retrofit.create(ArtistApi::class.java)
    }


    @Singleton
    @Provides
    fun provideSongApi(@ContentRetrofit retrofit: Retrofit): SongApi {
        return retrofit.create(SongApi::class.java)
    }


}