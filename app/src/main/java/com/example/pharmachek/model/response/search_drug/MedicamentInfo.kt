package com.example.pharmachek.model.response.search_drug

import androidx.room.Entity
import com.example.pharmachek.utils.Constants
import com.google.gson.annotations.SerializedName

data class MedicamentInfo(
    val barcode: String,
    @SerializedName("country_of_origin")
    val countryOfOrigin: String?,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("ins_composition")
    val insComposition: String?,
    @SerializedName("ins_contraindications")
    val insContraindications: String?,
    @SerializedName("ins_dosage_and_administration")
    val insDosageAndAdministration: String?,
    @SerializedName("ins_indications")
    val insIndications: String?,
    @SerializedName("ins_interactions")
    val insInteractions: String?,
    @SerializedName("ins_manufacturer_info")
    val insManufacturerInfo: String?,
    @SerializedName("ins_overdose")
    val insOverdose: String?,
    @SerializedName("ins_pharmacological_properties")
    val insPharmacologicalProperties: String?,
    @SerializedName("ins_release_form")
    val insReleaseForm: String?,
    @SerializedName("ins_shelf_life")
    val insShelfLife: String?,
    @SerializedName("ins_side_effects")
    val insSideEffects: String?,
    @SerializedName("ins_special_conditions")
    val insSpecialConditions: String?,
    @SerializedName("ins_storage_conditions")
    val insStorageConditions: String?,
    val mass: String?,
    val mnn: String?,
    @SerializedName("name_en")
    val nameEn: String?,
    val negative: Int,
    val neutral: Int,
    val packaging: String?,
    val positive: Int,
    val producer: String?,
    @SerializedName("product_name")
    val productName: String?,
    val quantity: Double,
    @SerializedName("release_conditions")
    val releaseConditions: String?,
    val saved: Int,
    @SerializedName("short_name")
    val shortName: String?,
    val url: String?
)