package com.example.pharmachek.api

import com.example.pharmachek.model.request.RequestData
import com.example.pharmachek.model.response.ResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("chek_nom")
    fun getResponse(@Body requestData: RequestData) : Call<List<ResponseData>>

}