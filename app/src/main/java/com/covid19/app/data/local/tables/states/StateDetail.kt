package com.covid19.app.data.local.tables.states

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity
data class StateDetail (
    @PrimaryKey
    val state: String,
    val districts: List<DistrictDetail>
)

@Entity
data class DistrictDetail(
    @PrimaryKey
    val name: String,
    val confirmed: Int,
    val lastupdatedtime: String,
    val confirmed_delta: Int
)

object DistrictWiseDataConverters {
    @TypeConverter
    @JvmStatic
    fun fromDistrictData(districtData: List<DistrictDetail>?): String? {
        return Gson().toJson(districtData)
    }

    @TypeConverter
    @JvmStatic
    fun fromString(json: String?): List<DistrictDetail>? {
        val listType: Type = object : TypeToken<ArrayList<DistrictDetail>>() {}.type
        return Gson().fromJson(json, listType)
    }
}