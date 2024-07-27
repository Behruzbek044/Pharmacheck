package com.example.pharmachek.ui.aboutUs

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentAboutUsBinding
import com.example.pharmachek.utils.popBackStack


class AboutUsFragment : Fragment(R.layout.fragment_about_us) {

    private var _binding: FragmentAboutUsBinding? = null

    private val binding: FragmentAboutUsBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAboutUsBinding.bind(view)

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

    private fun FragmentAboutUsBinding.setupListeners() {
        iconBack.setOnClickListener {
            popBackStack()
        }
        telegramValue.setOnClickListener {
            startActivity(getTelegramInt(requireContext()))
        }
        youTubeValue.setOnClickListener {
            var youtubeIntent: Intent?
            try {
                youtubeIntent = Intent(ACTION_VIEW)
                youtubeIntent.setPackage("com.google.android.youtube")
                youtubeIntent.setData(Uri.parse("https://www.youtube.com/@pharmacheckuz"))
                startActivity(youtubeIntent)
            } catch (e: ActivityNotFoundException) {
                youtubeIntent = Intent(ACTION_VIEW)
                youtubeIntent.setData(Uri.parse("https://www.youtube.com/@pharmacheckuz"))
                startActivity(youtubeIntent)
            }
        }
    }

    private fun FragmentAboutUsBinding.setupValues() {
    }

    private fun FragmentAboutUsBinding.setupViews() {}

    private fun getTelegramInt(context: Context): Intent {
        val intent: Intent = try {
            try { // check for telegram app
                context.packageManager.getPackageInfo("org.telegram.messenger", 0)
            } catch (e: PackageManager.NameNotFoundException) {
                // check for telegram X app
                context.packageManager.getPackageInfo("org.thunderdog.challegram", 0)
            }
            // set app Uri
            Intent(ACTION_VIEW, Uri.parse("tg://resolve?domain=pharmacheck_uz}"))
        } catch (e: PackageManager.NameNotFoundException) {
            // set browser URI
            Intent(ACTION_VIEW, Uri.parse("http://www.telegram.me/pharmacheck_uz"))
        }
        return intent
    }
}