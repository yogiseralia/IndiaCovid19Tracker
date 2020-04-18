package com.covid19.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.covid19.app.data.local.tables.patient.Patient
import com.covid19.app.data.local.tables.patient.PatientDao
import com.covid19.app.data.local.tables.states.DistrictWiseDataConverters
import com.covid19.app.data.local.tables.states.StateAndDistrictsDao
import com.covid19.app.data.local.tables.states.StateDetail

@Database(entities = [Patient::class, StateDetail::class], version = 1, exportSchema = false)
@TypeConverters(DistrictWiseDataConverters::class)
abstract class Covid19Database : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: Covid19Database? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): Covid19Database {
            val build =
                Room.databaseBuilder(context, Covid19Database::class.java, "covid_19_app.db")
                    .build()
            return build
        }
    }

    abstract fun patientDao(): PatientDao
    abstract fun stateDataDao(): StateAndDistrictsDao
}