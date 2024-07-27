package com.example.pharmachek.api

import com.example.pharmachek.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRetrofit {
    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getParmacheckApiService(): ParmacheckApiService = getRetrofit().create(ParmacheckApiService::class.java)
}