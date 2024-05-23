package com.example.pharmachek.fragments.bottom_navigation_fragments

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.pharmachek.MainActivity
import com.example.pharmachek.R
import com.example.pharmachek.databinding.FragmentHomeBinding
import com.example.pharmachek.fragments.ItemFragment
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null

    private val binding: FragmentHomeBinding get() = _binding!!

    private var str = ""

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                isGranted: Boolean ->
            if (isGranted) {
                showCamera()
            }else{
                // Explain why you need permission
            }
        }

    private val scanLauncher =
        registerForActivityResult(ScanContract()) {
                result: ScanIntentResult ->
            run {
                if (result.contents == null) {
                    Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
                } else {
                    setResult(result.contents)
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        with(binding) {

            barcodeButton.setOnClickListener {
                checkPermissionCamera(requireContext())
            }

        }
    }

    private fun navigateTo(fragment: Fragment) {
        parentFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack(null).commit()
    }

    private fun setResult(string: String) {
        str = string

        val itemFragment = ItemFragment()
        val itemFragmentBundle = Bundle().apply {
            putString("str", str)
        }
        itemFragment.arguments = itemFragmentBundle
        navigateTo(itemFragment)
        (requireActivity() as MainActivity).hideBottomNavigation()
    }

    private fun showCamera() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
        options.setPrompt("Scan QR code")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        options.setOrientationLocked(false)

        scanLauncher.launch(options)

    }

    private fun checkPermissionCamera(context: Context) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            showCamera()
        }else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)){
            Toast.makeText(context, "Camera permission required", Toast.LENGTH_SHORT).show()
        }else {
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

}