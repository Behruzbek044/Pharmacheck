package com.example.pharmachek.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pharmachek.MainActivity
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment(R.layout.fragment_about_us) {

    private var _binding: FragmentAboutUsBinding? = null

    private val binding: FragmentAboutUsBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAboutUsBinding.bind(view)

        with(binding){
            backButton.setOnClickListener {
                (requireActivity() as MainActivity).showBottomNavigation()
                parentFragmentManager.popBackStack()
            }
        }

    }

}