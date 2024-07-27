package com.example.pharmachek.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pharmachek.utils.Constants
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(Constants.PARMACHECK_ENTITY)
data class ParmacheckEntity(
    @PrimaryKey(autoGenerate = true)
    val barcode: Long,
    val countryOfOrigin: String?,
    val imageUrls: String,
    val insComposition: String?,
    val insContraindications: String?,
    val insDosageAndAdministration: String?,
    val insIndications: String?,
    val insInteractions: String?,
    val insManufacturerInfo: String?,
    val insOverdose: String?,
    val insPharmacologicalProperties: String?,
    val insReleaseForm: String?,
    val insShelfLife: String?,
    val insSideEffects: String?,
    val insSpecialConditions: String?,
    val insStorageConditions: String?,
    val mass: String?,
    val mnn: String?,
    val nameEn: String?,
    val negative: Int,
    val neutral: Int,
    val packaging: String?,
    val positive: Int,
    val producer: String?,
    val productName: String?,
    val quantity: Double,
    val releaseConditions: String?,
    val saved: Int,
    val shortName: String?,
    val url: String?
): Serializable