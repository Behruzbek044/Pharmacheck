package com.example.pharmachek.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentChatBinding
import com.example.pharmachek.databinding.FragmentHomeBinding

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private var _binding: FragmentChatBinding? = null

    private val binding: FragmentChatBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChatBinding.bind(view)

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

    private fun FragmentChatBinding.setupListeners() {}

    private fun FragmentChatBinding.setupValues() {
    }

    private fun FragmentChatBinding.setupViews() {}
}