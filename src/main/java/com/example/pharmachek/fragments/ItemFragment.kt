package com.example.pharmachek.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pharmachek.R
import com.example.pharmachek.api.ApiService
import com.example.pharmachek.databinding.FragmentItemBinding
import com.example.pharmachek.model.request.RequestData
import com.example.pharmachek.model.response.ResponseData
import com.example.pharmachek.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemFragment : Fragment(R.layout.fragment_item) {

    private var _binding: FragmentItemBinding? = null

    private val binding: FragmentItemBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentItemBinding.bind(view)

        val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build()

        val apiService = retrofit.create(ApiService::class.java)

        val str = arguments?.getString("str") ?: ""

        val opr = "ch"
        val det = str

        val requestData = RequestData(opr, det)

        apiService.getResponse(requestData).enqueue(object : Callback<List<ResponseData>> {

            override fun onResponse(
                call: Call<List<ResponseData>>,
                response: Response<List<ResponseData>>
            ) {
                Log.d("javob", "${response.body()}")
                if (response.isSuccessful) {
                    val data = response.body()

                    data?.let {
                        with(binding) {
                            var positiv = it[0].positiv * 100
                            var neutral = it[0].neutral * 100
                            var negative = it[0].negative * 100
                            greenText.text = positiv.toString()
                            yellowText.text = neutral.toString()
                            redText.text = negative.toString()
                            svetoforCv.visibility = View.VISIBLE
                            progressBarName.visibility = View.INVISIBLE
                            drugImage.visibility = View.VISIBLE
                            drugName.visibility = View.VISIBLE
                            descriptionText.visibility = View.VISIBLE
                            descriptionTv.visibility = View.VISIBLE
                        }
                    }
                }
            }

            override fun onFailure(p0: Call<List<ResponseData>>, p1: Throwable) {
                Log.d("Misal", "onFailure: ${p1.message}")
            }

        })

    }
}