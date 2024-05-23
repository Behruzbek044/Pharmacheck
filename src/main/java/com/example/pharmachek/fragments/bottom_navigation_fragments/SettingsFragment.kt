package com.example.pharmachek.fragments.bottom_navigation_fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pharmachek.MainActivity
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentSettingsBinding
import com.example.pharmachek.fragments.AboutUsFragment
import com.example.pharmachek.fragments.LanguageFragment

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null

    private val binding: FragmentSettingsBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

        with(binding) {

            tilTv.setOnClickListener {
                navigateTo(LanguageFragment())
                (requireActivity() as MainActivity).hideBottomNavigation()
            }

            rejimTv.setOnClickListener {
                Toast.makeText(requireContext(), "Hozirda dasturda faqat bitta rejim mavjud",Toast.LENGTH_SHORT).show()
            }

            aboutUsTv.setOnClickListener {
                navigateTo(AboutUsFragment())
                (requireActivity() as MainActivity).hideBottomNavigation()
            }

        }
    }

    //Navigate Function
    private fun navigateTo(fragment: Fragment) {
        parentFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack(null).commit()
    }

}