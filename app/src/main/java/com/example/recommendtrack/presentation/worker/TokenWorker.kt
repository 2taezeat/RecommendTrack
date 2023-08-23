package com.example.recommendtrack.presentation.worker

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.recommendtrack.BuildConfig
import com.example.recommendtrack.domain.usecase.token.WorkerGetTokenUseCase
import com.example.recommendtrack.utils.Constants
import com.example.recommendtrack.utils.PreferenceKey
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class TokenWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val dataStore: DataStore<Preferences>,
    private val workerGetTokenUseCase: WorkerGetTokenUseCase,
) : CoroutineWorker(appContext, workerParams) {


    override suspend fun doWork(): Result {
        Timber.d("worker_doWork")

        return try {
            getToken()
            Result.success()
        } catch (exception: Exception) {
            Result.failure()
        }
    }


    private suspend fun getToken() {
        Timber.d("worker_getToken_Call")
        val tokenRemoteFlow = workerGetTokenUseCase(
            Constants.SPOTIFY_GRANT_TYPE,
            BuildConfig.SPOTIFY_CLIENT_ID,
            BuildConfig.SPOTIFY_CLIENT_SECRET
        )
        tokenRemoteFlow.collect { token -> writeTokenDataStore(token.accessToken) }

    }

    private suspend fun writeTokenDataStore(str: String) {
        Timber.d("worker_writeTokenDataStore_Call")
        dataStore.edit { preferences -> preferences[PreferenceKey.tokenPreferenceKey] = str }
    }


}
