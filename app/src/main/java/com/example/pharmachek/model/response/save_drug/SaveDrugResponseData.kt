package com.example.pharmachek.model.response.save_drug

import com.google.gson.annotations.SerializedName

data class SaveDrugResponseData(
    val info: String,
    @SerializedName("medicament_info")
    val medicamentInfo: MedicamentInfo
)