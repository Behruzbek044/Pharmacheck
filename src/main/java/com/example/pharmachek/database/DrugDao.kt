package com.example.pharmachek.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.pharmachek.models.DrugData

@Dao
interface DrugDao {

    @Update
    suspend fun updateDrug(drugData: DrugData)

    @Query("SELECT * FROM akbarali_final")
    fun getAllDrug(): List<DrugData>

    @Query("SELECT * FROM akbarali_final WHERE barcode=:br")
    fun getDrugByBarcode(br:String): DrugData?

}