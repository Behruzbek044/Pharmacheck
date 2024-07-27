package com.example.pharmachek.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pharmachek.R
import com.example.pharmachek.database.ParmacheckDatabase
import com.example.pharmachek.database.ParmacheckEntity
import com.example.pharmachek.databinding.FragmentDetailBinding
import com.example.pharmachek.utils.popBackStack
import com.example.pharmachek.utils.setNavigationBarColor
import com.example.pharmachek.utils.toastMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null

    private val binding: FragmentDetailBinding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        with(binding) {
            updateUI()
            setupValues()
            setupViews()
            setupListeners()
        }
    }

    private fun updateUI() {
        setNavigationBarColor(R.color.background_color)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun FragmentDetailBinding.setupListeners() {
        iconBack.setOnClickListener {
            popBackStack()
        }
    }

    private fun FragmentDetailBinding.setupValues() {}

    private fun FragmentDetailBinding.setupViews() {
        Glide.with(requireContext()).load(args.drugImage.replace("'", "")).into(drugImage)
        drugName.text = args.drugName
        firstIndicator.progress = args.drugNegative
        firstIndicatorValue.text = args.drugNegative.toString()
        secondIndicator.progress = args.drugNeutral
        secondIndicatorValue.text = args.drugNeutral.toString()
        thirdIndicator.progress = args.drugPositive
        thirdIndicatorValue.text = args.drugPositive.toString()
        if (!args.composition.equals("null")) {
            compositionDrugText.text = args.composition
        }
        checkAndSetDrugDatasToView(args.composition, compositionDrugTitle, compositionDrugText)
        checkAndSetDrugDatasToView(
            args.pharmacologicalProperties,
            pharmacologicalPropertiesDrugTitle,
            pharmacologicalPropertiesDrugText
        )
        checkAndSetDrugDatasToView(args.composition, compositionDrugTitle, compositionDrugText)
        checkAndSetDrugDatasToView(
            args.dosageAndAdministration,
            dosageAndAdministrationDrugTitle,
            dosageAndAdministrationDrugText
        )
        checkAndSetDrugDatasToView(args.sideEffects, sideEffectsDrugTitle, sideEffectsDrugText)
        checkAndSetDrugDatasToView(
            args.contraindications, contraindicationsDrugTitle, contraindicationsDrugText
        )
        checkAndSetDrugDatasToView(args.overdose, overdoseDrugTitle, overdoseDrugText)
        checkAndSetDrugDatasToView(
            args.specialConditions, specialConditionsDrugTitle, specialConditionsDrugText
        )
        checkAndSetDrugDatasToView(args.interactions, interactionsDrugTitle, interactionsDrugText)
        checkAndSetDrugDatasToView(
            args.storageConditions, storageConditionsDrugTitle, storageConditionsDrugText
        )
        checkAndSetDrugDatasToView(args.shelfLife, shelfLifeDrugTitle, shelfLifeDrugText)
        checkAndSetDrugDatasToView(args.releaseForm, releaseFormDrugTitle, releaseFormDrugText)
        checkAndSetDrugDatasToView(
            args.manufacturerInfo, manufacturerInfoDrugTitle, manufacturerInfoDrugText
        )
        checkAndSetDrugDatasToView(args.mass, massDrugTitle, massDrugText)
        checkAndSetDrugDatasToView(args.indications, indicationsDrugTitle, indicationsDrugText)
        checkAndSetDrugDatasToView(args.producer, producerDrugTitle, producerDrugText)
    }

    private fun checkAndSetDrugDatasToView(
        data: String, dataTitleView: TextView, dataView: TextView
    ) {
        if (data != "null") {
            dataTitleView.visibility = View.VISIBLE
            dataView.visibility = View.VISIBLE
            dataView.text = data
        }
    }
}