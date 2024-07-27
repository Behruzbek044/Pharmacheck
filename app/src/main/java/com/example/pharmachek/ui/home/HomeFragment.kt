package com.example.pharmachek.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pharmachek.R
import com.example.pharmachek.api.ApiRetrofit
import com.example.pharmachek.database.ParmacheckDatabase
import com.example.pharmachek.databinding.FragmentHomeBinding
import com.example.pharmachek.model.request.search_drug.SearchDrugRequestData
import com.example.pharmachek.model.response.search_drug.SearchDrugResponseData
import com.example.pharmachek.utils.Constants
import com.example.pharmachek.utils.navigateTo
import com.example.pharmachek.utils.toastMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var drugsAdapter: HomeDrugsAdapter

    private var _binding: FragmentHomeBinding? = null

    private val binding: FragmentHomeBinding get() = _binding!!
    private val TAG = "HomeFragmentAAA"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        with(binding) {
            setupValues()
            setupViews()
            setupListeners()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun FragmentHomeBinding.setupListeners() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(
                searchText: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                searchText?.let { searchText ->
                    if (searchText.isNotEmpty()) {
                        iconSearch.imageTintList = requireContext().getColorStateList(R.color.black)
                        noDrugsText.visibility = View.INVISIBLE
                        progressBar.visibility = View.VISIBLE
                    } else {
                        iconSearch.imageTintList =
                            requireContext().getColorStateList(R.color.dark_gray)
                        noDrugsText.visibility = View.VISIBLE
                        progressBar.visibility = View.INVISIBLE
                        recyclerViewDrugs.visibility = View.INVISIBLE
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                ApiRetrofit.getParmacheckApiService()
                    .searchDrug(SearchDrugRequestData(s.toString()))
                    .enqueue(object :
                        Callback<List<SearchDrugResponseData>> {
                        override fun onResponse(
                            call: Call<List<SearchDrugResponseData>>,
                            response: Response<List<SearchDrugResponseData>>
                        ) {
                            response.body()?.let {
                                when (it.first().info) {
                                    Constants.SEARCH_DRUG_STATE_SUCCESS -> {
                                        drugsAdapter.submitList(it)

                                        noDrugsText.visibility = View.INVISIBLE
                                        progressBar.visibility = View.INVISIBLE
                                        recyclerViewDrugs.visibility = View.VISIBLE
                                        if (searchEditText.text.toString()
                                                .isNotEmpty()
                                        ) recyclerViewDrugs.visibility = View.VISIBLE
                                    }

                                    Constants.SEARCH_DRUG_STATE_ERROR -> {
                                        progressBar.visibility = View.INVISIBLE
                                        noDrugsText.visibility = View.VISIBLE
                                        recyclerViewDrugs.visibility = View.INVISIBLE
                                    }

                                    else -> {}
                                }
                            }
                        }

                        override fun onFailure(
                            call: Call<List<SearchDrugResponseData>>,
                            throwable: Throwable
                        ) {
                            Log.d(TAG, "onFailure: ${throwable.localizedMessage}")
                        }
                    })
            }
        })
        drugsAdapter.setOnItemClickListener { searchDrugResponseData, i ->
            val navigation = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                searchDrugResponseData.medicamentInfo.imageUrls.first(),
                searchDrugResponseData.medicamentInfo.productName.toString(),
                searchDrugResponseData.medicamentInfo.insComposition.toString(),
                searchDrugResponseData.medicamentInfo.positive,
                searchDrugResponseData.medicamentInfo.negative,
                searchDrugResponseData.medicamentInfo.neutral,
                searchDrugResponseData.medicamentInfo.insPharmacologicalProperties.toString(),
                searchDrugResponseData.medicamentInfo.insDosageAndAdministration.toString(),
                searchDrugResponseData.medicamentInfo.insSideEffects.toString(),
                searchDrugResponseData.medicamentInfo.insContraindications.toString(),
                searchDrugResponseData.medicamentInfo.insOverdose.toString(),
                searchDrugResponseData.medicamentInfo.insSpecialConditions.toString(),
                searchDrugResponseData.medicamentInfo.insInteractions.toString(),
                searchDrugResponseData.medicamentInfo.insStorageConditions.toString(),
                searchDrugResponseData.medicamentInfo.insShelfLife.toString(),
                searchDrugResponseData.medicamentInfo.insReleaseForm.toString(),
                searchDrugResponseData.medicamentInfo.insManufacturerInfo.toString(),
                searchDrugResponseData.medicamentInfo.mass.toString(),
                searchDrugResponseData.medicamentInfo.insIndications.toString(),
                "${searchDrugResponseData.medicamentInfo.producer.toString()}, ${searchDrugResponseData.medicamentInfo.countryOfOrigin.toString()}",
            )
            navigateTo(navigation)
        }
        drugsAdapter.setOnFavoriteButtonClickListener { searchDrugResponseData, i ->
            val database = ParmacheckDatabase.createDatabase(requireContext())
            val dao = database.parmacheckDao()
            lifecycleScope.launch(Dispatchers.IO) {
                val addDrugResponse = dao.addDrug(searchDrugResponseData.toParmacheckEntity())
                withContext(Dispatchers.Main) {
                    if (addDrugResponse > 0) {
                        toastMessage("Dori muvaffaqiyatli saqlandi")
                    }
                }
            }
        }
    }

    private fun FragmentHomeBinding.setupValues() {
        drugsAdapter = HomeDrugsAdapter()
    }

    private fun FragmentHomeBinding.setupViews() {
        val drugsRecyclerLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        recyclerViewDrugs.layoutManager = drugsRecyclerLayoutManager
        recyclerViewDrugs.adapter = drugsAdapter
    }
}