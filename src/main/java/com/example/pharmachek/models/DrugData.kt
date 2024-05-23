package com.example.pharmachek.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "akbarali_final")
data class DrugData(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("medicine_name")
    val medicine_name: String,
    @ColumnInfo("instruction_ru_file_sha")
    val instruction_ru_file_sha: String,
    @ColumnInfo("instruction_uz_file_sha")
    val instruction_uz_file_sha: String,
    @ColumnInfo("producer_name")
    val producer_name: String,
    @ColumnInfo("certificate_number")
    val certificate_number: String,
    @ColumnInfo("barcode")
    val barcode: String,
    @ColumnInfo("name_rus")
    val name_rus: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("saved")
    val saved: Int,
    @ColumnInfo("image")
    val image: String
)
