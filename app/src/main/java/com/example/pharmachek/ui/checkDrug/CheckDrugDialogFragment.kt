package com.example.pharmachek.ui.checkDrug

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.pharmachek.R
import com.example.pharmachek.databinding.DialogFragmentCheckDrugBinding
import com.example.pharmachek.utils.navigateTo
import com.example.pharmachek.utils.popBackStack

class CheckDrugDialogFragment : DialogFragment(R.layout.dialog_fragment_check_drug) {

    private var _binding: DialogFragmentCheckDrugBinding? = null

    private val binding: DialogFragmentCheckDrugBinding get() = _binding!!
    private val args: CheckDrugDialogFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DialogFragmentCheckDrugBinding.bind(view)

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

    private fun DialogFragmentCheckDrugBinding.setupListeners() {
        btnAdd.setOnClickListener {
            val direcition =
                CheckDrugDialogFragmentDirections.actionCheckDrugDialogFragmentToAddDrugDialogFragment(
                    args.barcodeText
                )
            navigateTo(direcition)
        }
        btnBack.setOnClickListener {
            popBackStack()
        }
    }

    private fun DialogFragmentCheckDrugBinding.setupValues() {
    }

    private fun DialogFragmentCheckDrugBinding.setupViews() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
    }
}