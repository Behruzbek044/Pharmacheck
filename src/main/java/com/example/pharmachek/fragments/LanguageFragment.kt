package com.example.pharmachek.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.example.pharmachek.MainActivity
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentLanguageBinding

class LanguageFragment : Fragment(R.layout.fragment_language) {

    private var _binding: FragmentLanguageBinding? = null

    private val binding: FragmentLanguageBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLanguageBinding.bind(view)

        with(binding) {

            saveBtn.setOnClickListener {
                (requireActivity() as MainActivity).showBottomNavigation()
                parentFragmentManager.popBackStack()
            }

            backButton.setOnClickListener {
                (requireActivity() as MainActivity).showBottomNavigation()
                parentFragmentManager.popBackStack()
            }

        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (requireActivity() as MainActivity).showBottomNavigation()
                parentFragmentManager.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

}