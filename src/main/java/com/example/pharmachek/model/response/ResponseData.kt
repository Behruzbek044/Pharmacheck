package com.example.pharmachek.model.response

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("sht_kod")
    val sht_kod: String,
    @SerializedName("positiv")
    val positiv: Double,
    @SerializedName("neutral")
    val neutral: Double,
    @SerializedName("negative")
    val negative: Double,
    @SerializedName("date")
    val date: Long
)
