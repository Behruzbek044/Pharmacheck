package com.example.pharmachek.api

import com.example.pharmachek.model.request.check_drug.CheckDrugRequestData
import com.example.pharmachek.model.request.save_drug.SaveDrugRequestData
import com.example.pharmachek.model.request.search_drug.SearchDrugRequestData
import com.example.pharmachek.model.response.check_drug.CheckDrugResponseData
import com.example.pharmachek.model.response.save_drug.SaveDrugResponseData
import com.example.pharmachek.model.response.search_drug.SearchDrugResponseData
import com.example.pharmachek.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ParmacheckApiService {

    @POST(Constants.CHECK_ENDPOINT)
    fun checkDrug(@Body checkDrugRequestData: CheckDrugRequestData): Call<List<CheckDrugResponseData>>

    @POST(Constants.SAVE_ENDPOINT)
    fun saveDrug(@Body saveDrugRequestData: SaveDrugRequestData): Call<SaveDrugResponseData>

    @POST(Constants.SEARCH_ENDPOINT)
    fun searchDrug(@Body searchDrugRequestData: SearchDrugRequestData): Call<List<SearchDrugResponseData>>
}