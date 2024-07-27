package com.example.pharmachek.ui.support

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentChatBinding
import com.example.pharmachek.databinding.FragmentHomeBinding
import com.example.pharmachek.databinding.FragmentSupportBinding
import com.example.pharmachek.utils.popBackStack
import com.example.pharmachek.utils.toastMessage

class SupportFragment : Fragment(R.layout.fragment_support) {

    private var _binding: FragmentSupportBinding? = null

    private val binding: FragmentSupportBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSupportBinding.bind(view)

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

    private fun FragmentSupportBinding.setupListeners() {
        iconBack.setOnClickListener {
            popBackStack()
        }
        phoneValue.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:${phoneValue.text}")
            startActivity(dialIntent)
        }
        gmailValue.setOnClickListener {
            sendEmail(gmailValue.text.toString(), "", "")
        }
    }

    private fun FragmentSupportBinding.setupValues() {
    }

    private fun FragmentSupportBinding.setupViews() {}

    @SuppressLint("IntentReset")
    private fun sendEmail(recipient: String, subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)
        try {
            startActivity(Intent.createChooser(mIntent, "Gmailni tanlang"))
        }
        catch (e: Exception){
            toastMessage(e.message.toString())
        }

    }
}