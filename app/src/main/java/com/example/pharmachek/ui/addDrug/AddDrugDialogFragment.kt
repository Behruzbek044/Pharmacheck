package com.example.pharmachek.ui.addDrug

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.pharmachek.R
import com.example.pharmachek.api.ApiRetrofit
import com.example.pharmachek.databinding.DialogFragmentAddDrugBinding
import com.example.pharmachek.model.request.save_drug.SaveDrugRequestData
import com.example.pharmachek.model.response.save_drug.SaveDrugResponseData
import com.example.pharmachek.ui.addDrug.AddDrugDialogFragmentDirections
import com.example.pharmachek.utils.Constants
import com.example.pharmachek.utils.navigateTo
import com.example.pharmachek.utils.popBackStack
import com.example.pharmachek.utils.toastMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDrugDialogFragment : DialogFragment(R.layout.dialog_fragment_add_drug) {

    private var _binding: DialogFragmentAddDrugBinding? = null

    private val binding: DialogFragmentAddDrugBinding get() = _binding!!
    private val args: AddDrugDialogFragmentArgs by navArgs()
    private val TAG = "AddDrugDialogFragmentAAA"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DialogFragmentAddDrugBinding.bind(view)

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

    private fun DialogFragmentAddDrugBinding.setupListeners() {
        btnBack.setOnClickListener {
            popBackStack()
        }
        btnAdd.setOnClickListener {
            val drugName = drugNameEditText.text.toString().trim()
            Log.d(TAG, "data: $drugName, ${args.barcodeText}")
            if (drugName.isNotEmpty()) {
                ApiRetrofit.getParmacheckApiService()
                    .saveDrug(SaveDrugRequestData(drugName, args.barcodeText)).enqueue(object :
                    Callback<SaveDrugResponseData> {
                    override fun onResponse(
                        call: Call<SaveDrugResponseData>,
                        response: Response<SaveDrugResponseData>
                    ) {
                        response.body()?.let {
                            when (it.info) {
                                Constants.SAVE_DRUG_STATE_SUCCESS -> {
                                    toastMessage(it.info)
                                    val direction = AddDrugDialogFragmentDirections.actionAddDrugDialogFragmentToSendedDrugDialogFragment()
                                    navigateTo(direction)
                                }

                                Constants.SAVE_DRUG_STATE_ERROR -> {
                                    toastMessage("Xatolik: ${it.info}")
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<SaveDrugResponseData>, throwable: Throwable) {
                        Log.d(TAG, "onFailure: ${throwable.localizedMessage}")
                    }
                })
            } else {
                toastMessage("Dori nomini kiriting")
            }
        }
    }

    private fun DialogFragmentAddDrugBinding.setupValues() {
        barcodeText.text = args.barcodeText
    }

    private fun DialogFragmentAddDrugBinding.setupViews() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
    }
}