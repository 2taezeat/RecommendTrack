package com.example.recommendtrack.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.recommendtrack.presentation.worker.TokenWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val workManager: WorkManager
) : ViewModel() {


    init {
        setTokenWork()
    }


    private fun setTokenWork() {
        Timber.d("setTokenWork")
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val tokenWorkRequest =
            PeriodicWorkRequestBuilder<TokenWorker>(1, TimeUnit.HOURS, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        workManager.enqueueUniquePeriodicWork(
            TOKEN_WORKER_KEY,
            ExistingPeriodicWorkPolicy.UPDATE,
            tokenWorkRequest
        )
    }

    fun getTokenWorkStatus(): LiveData<MutableList<WorkInfo>> =
        workManager.getWorkInfosForUniqueWorkLiveData(TOKEN_WORKER_KEY) // IN PeriodicWork, ENQUEUED >> RUNNING >> ENQUEUED >> RUNNING >> ...


    companion object {
        private const val TOKEN_WORKER_KEY = "token_worker"
    }


}