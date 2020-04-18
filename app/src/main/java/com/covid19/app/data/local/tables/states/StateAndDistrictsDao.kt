package com.covid19.app.data.local.tables.states

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StateAndDistrictsDao {

    @Query("SELECT * FROM StateDetail")
    fun getAll(): List<StateDetail>

    @Query("SELECT * FROM StateDetail WHERE state IS (:statename)")
    fun loadByStateName(statename: String): StateDetail

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg users: StateDetail)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(users: StateDetail)
}