package com.example.pharmachek.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentOnboardingBinding
import com.example.pharmachek.utils.SharedPrefHelper
import com.example.pharmachek.utils.navigateTo
import com.example.pharmachek.utils.setLightNavigationBar
import com.example.pharmachek.utils.setLightStatusBar
import com.example.pharmachek.utils.setNavigationBarColor
import com.example.pharmachek.utils.setStatusBarColor

class OnBoardingFragment : Fragment(R.layout.fragment_onboarding) {

    private lateinit var sharedPrefHelper: SharedPrefHelper

    private var _binding: FragmentOnboardingBinding? = null
    private val binding: FragmentOnboardingBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnboardingBinding.bind(view)

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
        setStatusBarColor(R.color.splash_background)
        setLightStatusBar(true)
        setNavigationBarColor(R.color.bottom_circle_frame)
        setLightNavigationBar(false)
    }

    private fun FragmentOnboardingBinding.setupListeners() {
        iconNext.setOnClickListener {
            val navigation = OnBoardingFragmentDirections.actionOnBoardingFragmentToMainFragment()
            navigateTo(navigation)
            sharedPrefHelper.hasOnboardingScreenOpened = true
        }
    }

    private fun FragmentOnboardingBinding.setupValues() {
        sharedPrefHelper = SharedPrefHelper(requireContext())
    }

    private fun FragmentOnboardingBinding.setupViews() {}
}