package com.covid19.app.data.shared

import com.covid19.app.data.local.tables.patient.Patient
import com.covid19.app.data.local.tables.states.StateDetail

interface Covid19Repository {
    suspend fun getPatientData(): Result<List<Patient>>
    suspend fun getStateDistrictData() : Result<List<StateDetail>>
//    suspend fun getDailyChangesOfStates()
//    suspend fun getTimelineData()
//    suspend fun getTravelHistoryData()
}