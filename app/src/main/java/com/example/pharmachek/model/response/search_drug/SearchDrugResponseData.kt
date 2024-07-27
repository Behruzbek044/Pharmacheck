package com.example.pharmachek.model.response.search_drug

import com.example.pharmachek.database.ParmacheckEntity
import com.google.gson.annotations.SerializedName

data class SearchDrugResponseData(
    val info: String,
    @SerializedName("medicament_info")
    val medicamentInfo: MedicamentInfo
) {
    fun toParmacheckEntity(): ParmacheckEntity {
        return ParmacheckEntity(
            barcode = medicamentInfo.barcode.toLong(),
            countryOfOrigin = medicamentInfo.countryOfOrigin,
            imageUrls = medicamentInfo.imageUrls.first(),
            insComposition = medicamentInfo.insComposition,
            insContraindications = medicamentInfo.insContraindications,
            insDosageAndAdministration = medicamentInfo.insDosageAndAdministration,
            insIndications = medicamentInfo.insIndications,
            insInteractions = medicamentInfo.insInteractions,
            insManufacturerInfo = medicamentInfo.insManufacturerInfo,
            insOverdose = medicamentInfo.insOverdose,
            insPharmacologicalProperties = medicamentInfo.insPharmacologicalProperties,
            insReleaseForm = medicamentInfo.insReleaseForm,
            insShelfLife = medicamentInfo.insShelfLife,
            insSideEffects = medicamentInfo.insSideEffects,
            insSpecialConditions = medicamentInfo.insSpecialConditions,
            insStorageConditions = medicamentInfo.insStorageConditions,
            mass = medicamentInfo.mass,
            mnn = medicamentInfo.mnn,
            nameEn = medicamentInfo.nameEn,
            negative = medicamentInfo.negative,
            neutral = medicamentInfo.neutral,
            packaging = medicamentInfo.packaging,
            positive = medicamentInfo.positive,
            producer = medicamentInfo.producer,
            productName = medicamentInfo.productName,
            quantity = medicamentInfo.quantity,
            releaseConditions = medicamentInfo.releaseConditions,
            saved = medicamentInfo.saved,
            shortName = medicamentInfo.shortName,
            url = medicamentInfo.url
        )
    }
}