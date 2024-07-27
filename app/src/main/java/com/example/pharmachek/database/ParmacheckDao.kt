package com.example.pharmachek.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pharmachek.utils.Constants

@Dao
interface ParmacheckDao {

    @Query("SELECT * FROM ${Constants.PARMACHECK_ENTITY}")
    fun getDrugs(): List<ParmacheckEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDrug(drug: ParmacheckEntity): Long

    @Delete
    suspend fun deleteDrug(drug: ParmacheckEntity)
}