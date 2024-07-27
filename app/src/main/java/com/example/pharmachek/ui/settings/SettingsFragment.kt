package com.example.pharmachek.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentSettingsBinding
import com.example.pharmachek.utils.navigateTo

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null

    private val binding: FragmentSettingsBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

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

    private fun FragmentSettingsBinding.setupListeners() {
        helpContainer.setOnClickListener {
            navigateTo(R.id.action_settingsFragment_to_supportFragment)
        }
        aboutContainer.setOnClickListener {
            navigateTo(R.id.action_settingsFragment_to_aboutUsFragment)
        }
    }

    private fun FragmentSettingsBinding.setupValues() {
    }

    private fun FragmentSettingsBinding.setupViews() {}
}