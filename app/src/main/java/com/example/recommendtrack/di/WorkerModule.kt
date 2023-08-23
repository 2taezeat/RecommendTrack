package com.example.recommendtrack.di

import android.content.Context
import androidx.work.WorkManager
import com.example.recommendtrack.domain.repository.TokenRepository
import com.example.recommendtrack.domain.usecase.token.WorkerGetTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {
    @Provides
    @Singleton
    fun provideTokenWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)



    @Provides
    @Singleton
    fun provideWorkerGetTokenUseCase(repository: TokenRepository): WorkerGetTokenUseCase  {
        return WorkerGetTokenUseCase(repository)
    }

}