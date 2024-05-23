package com.example.pharmachek.fragments.bottom_navigation_fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentCatalogBinding

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private var _binding: FragmentCatalogBinding? = null

    private val binding: FragmentCatalogBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCatalogBinding.bind(view)
    }

}