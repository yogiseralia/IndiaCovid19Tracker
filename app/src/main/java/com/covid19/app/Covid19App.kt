package com.covid19.app

import androidx.multidex.MultiDexApplication
import androidx.work.*
import com.covid19.app.data.workers.DataUpdateWorker
import java.util.concurrent.TimeUnit

class Covid19App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        app= this
        setupWorkers()
    }

    companion object {
        var app: Covid19App? = null
    }

    private fun setupWorkers() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val saveRequest =
            PeriodicWorkRequestBuilder<DataUpdateWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "Covid19_App_Data_Sync",
            ExistingPeriodicWorkPolicy.KEEP, saveRequest
        )
    }
}