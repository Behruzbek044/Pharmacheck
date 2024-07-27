package com.example.pharmachek.ui.sendedDrug

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.pharmachek.R
import com.example.pharmachek.databinding.DialogFragmentSendedDrugBinding
import com.example.pharmachek.utils.popBackStack

class SendedDrugDialogFragment : DialogFragment(R.layout.dialog_fragment_sended_drug) {

    private var _binding: DialogFragmentSendedDrugBinding? = null

    private val binding: DialogFragmentSendedDrugBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DialogFragmentSendedDrugBinding.bind(view)

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

    private fun DialogFragmentSendedDrugBinding.setupListeners() {
        btnBack.setOnClickListener {
            popBackStack()
        }
    }

    private fun DialogFragmentSendedDrugBinding.setupValues() {
    }

    private fun DialogFragmentSendedDrugBinding.setupViews() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
    }
}