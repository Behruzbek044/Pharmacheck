package com.example.pharmachek.fragments.bottom_navigation_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentSavedBinding

class SavedFragment : Fragment(R.layout.fragment_saved) {

    private var _binding: FragmentSavedBinding? = null

    private val binding: FragmentSavedBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSavedBinding.bind(view)
    }

}