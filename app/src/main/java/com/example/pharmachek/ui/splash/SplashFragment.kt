package com.example.pharmachek.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentSplashBinding
import com.example.pharmachek.utils.SharedPrefHelper
import com.example.pharmachek.utils.navigateTo
import com.example.pharmachek.utils.setLightNavigationBar
import com.example.pharmachek.utils.setLightStatusBar
import com.example.pharmachek.utils.setNavigationBarColor
import com.example.pharmachek.utils.setStatusBarColor

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var sharedPrefHelper: SharedPrefHelper

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSplashBinding.bind(view)

        with(binding) {
            updateUI()
            setupValues()
            setupViews()
            setupListeners()
        }

        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (sharedPrefHelper.hasOnboardingScreenOpened) {
                    val navigation = SplashFragmentDirections.actionSplashFragmentToMainFragment()
                    navigateTo(navigation)
                } else {
                    val navigation = SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment()
                    navigateTo(navigation)
                }
            }, 3000
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI() {
        setStatusBarColor(R.color.splash_background)
        setLightStatusBar(true)
        setNavigationBarColor(R.color.splash_background)
        setLightNavigationBar(true)
    }

    private fun FragmentSplashBinding.setupListeners() {}

    private fun FragmentSplashBinding.setupValues() {
        sharedPrefHelper = SharedPrefHelper(requireContext())
    }

    private fun FragmentSplashBinding.setupViews() {}
}