package com.example.pharmachek.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.pharmachek.R
import com.example.pharmachek.api.ApiRetrofit
import com.example.pharmachek.databinding.FragmentMainBinding
import com.example.pharmachek.model.request.check_drug.CheckDrugRequestData
import com.example.pharmachek.model.response.check_drug.CheckDrugResponseData
import com.example.pharmachek.utils.Constants
import com.example.pharmachek.utils.navigateTo
import com.example.pharmachek.utils.setLightNavigationBar
import com.example.pharmachek.utils.setLightStatusBar
import com.example.pharmachek.utils.setNavigationBarColor
import com.example.pharmachek.utils.setStatusBarColor
import com.example.pharmachek.utils.toastMessage
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var navController: NavController
    private lateinit var barcodeLauncher: ActivityResultLauncher<ScanOptions>

    private var _binding: FragmentMainBinding? = null

    private val binding: FragmentMainBinding get() = _binding!!
    private val TAG = "MainFragmentAAA"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        with(binding) {
            updateUI()
            setupValues()
            setupViews()
            setupListeners()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI() {
        setStatusBarColor(R.color.background_color)
        setLightStatusBar(true)
        setNavigationBarColor(R.color.white)
        setLightNavigationBar(true)
    }

    private fun FragmentMainBinding.setupListeners() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.bookmarkFragment, R.id.chatFragment, R.id.settingsFragment -> {
                    scannerBarcodeFloatingActionButton.visibility = View.VISIBLE
                    bottomAppBar.visibility = View.VISIBLE
                    bottomNavigationView.visibility = View.VISIBLE
                }

                R.id.detailFragment, R.id.supportFragment, R.id.aboutUsFragment -> {
                    scannerBarcodeFloatingActionButton.visibility = View.GONE
                    bottomAppBar.visibility = View.GONE
                    bottomNavigationView.visibility = View.GONE
                }
            }
        }
        barcodeLauncher = registerForActivityResult(ScanContract()) {
            if (it.contents == null) {
                toastMessage("Bekor qilindi")
            } else {
                val barCode = it.contents
                Log.d(TAG, "barcode: $barCode")
                ApiRetrofit.getParmacheckApiService()
                    .checkDrug(CheckDrugRequestData("ch", barCode)).enqueue(object :
                        Callback<List<CheckDrugResponseData>> {
                        override fun onResponse(
                            call: Call<List<CheckDrugResponseData>>,
                            response: Response<List<CheckDrugResponseData>>
                        ) {
                            response.body()?.let {
                                when (it.first().info) {
                                    Constants.SEARCH_DRUG_STATE_SUCCESS -> {
                                        val direction =
                                            MainFragmentDirections.actionMainFragmentToDetailFragment(
                                                it.first().medicamentInfo.imageUrls.first(),
                                                it.first().medicamentInfo.productName.toString(),
                                                it.first().medicamentInfo.insComposition.toString(),
                                                it.first().medicamentInfo.positive,
                                                it.first().medicamentInfo.negative,
                                                it.first().medicamentInfo.neutral,
                                                it.first().medicamentInfo.insPharmacologicalProperties.toString(),
                                                it.first().medicamentInfo.insDosageAndAdministration.toString(),
                                                it.first().medicamentInfo.insSideEffects.toString(),
                                                it.first().medicamentInfo.insContraindications.toString(),
                                                it.first().medicamentInfo.insOverdose.toString(),
                                                it.first().medicamentInfo.insSpecialConditions.toString(),
                                                it.first().medicamentInfo.insInteractions.toString(),
                                                it.first().medicamentInfo.insStorageConditions.toString(),
                                                it.first().medicamentInfo.insShelfLife.toString(),
                                                it.first().medicamentInfo.insReleaseForm.toString(),
                                                it.first().medicamentInfo.insManufacturerInfo.toString(),
                                                it.first().medicamentInfo.mass.toString(),
                                                it.first().medicamentInfo.insIndications.toString(),
                                                "${it.first().medicamentInfo.producer.toString()}, ${it.first().medicamentInfo.countryOfRigin.toString()}",
                                            )
                                        navigateTo(direction)
                                    }

                                    Constants.SEARCH_DRUG_STATE_ERROR -> {
                                        val direction =
                                            MainFragmentDirections.actionMainFragmentToCheckDrugDialogFragment(
                                                barCode
                                            )
                                        navigateTo(direction)
                                    }

                                    else -> {
                                        val direction =
                                            MainFragmentDirections.actionMainFragmentToCheckDrugDialogFragment(
                                                barCode
                                            )
                                        navigateTo(direction)
                                    }
                                }
                            }
                        }

                        override fun onFailure(
                            call: Call<List<CheckDrugResponseData>>,
                            throwable: Throwable
                        ) {
                            Log.d(TAG, "onFailure: ${throwable.localizedMessage}")
                        }
                    })
            }
        }
        scannerBarcodeFloatingActionButton.setOnClickListener {
            val scanOptions = ScanOptions()
            scanOptions.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
            scanOptions.setPrompt("Dorining barkodini skanerlang")
            scanOptions.setCameraId(0)
            scanOptions.setBeepEnabled(true)
            scanOptions.setBarcodeImageEnabled(true)
            scanOptions.setOrientationLocked(false)

            barcodeLauncher.launch(scanOptions)
            barcodeLauncher.launch(ScanOptions())
        }
    }

    private fun FragmentMainBinding.setupValues() {
        navController = (childFragmentManager.findFragmentById(
            R.id.bottomNavigationFragmentContainerView
        ) as NavHostFragment).navController
        bottomNavigationView.setupWithNavController(navController)
    }

    private fun FragmentMainBinding.setupViews() {
        bottomNavigationView.menu.getItem(2).apply {
            isCheckable = false
            isEnabled = false
        }
    }
}