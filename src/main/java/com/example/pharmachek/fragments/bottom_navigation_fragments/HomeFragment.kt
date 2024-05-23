package com.example.pharmachek.fragments.bottom_navigation_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null

    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
    }

}