package com.covid19.app.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.covid19.app.data.local.Covid19Database
import com.covid19.app.data.remote.ApiClient
import com.covid19.app.data.remote.models.RawData
//import com.covid19.app.data.shared.Covid19DataRepoImpl

class DataUpdateWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
//    private val covid19DataRepo = Covid19DataRepoImpl()

    private val classTAG = DataUpdateWorker::class.java.simpleName
    override suspend fun doWork(): Result {
//        withContext(Dispatchers.IO) {
        Log.d(classTAG, "doWork called")
        val data: RawData = ApiClient.covid19APIService.rawData()
        Log.d(classTAG, "doWork rawData() called = $data")
//        val covid19Database = Covid19Database(context)

//        covid19Database.patientDao().insertAll(data.raw_data)
//        for (patient in covid19Database.patientDao().getAll()) {
//            println("patient = $patient")
//        }
        Log.d(classTAG, "doWork called complete")
//        }
        return Result.success()
    }
}