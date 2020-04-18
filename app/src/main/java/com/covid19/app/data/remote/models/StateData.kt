package com.covid19.app.data.remote.models

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


data class StateData(
    val stateName: String,
    val districtData: List<DistrictData>
)

data class DistrictData(
    val district: String,
    val confirmed: Int,
    val lastupdatedtime: String,
    val delta: Delta
)