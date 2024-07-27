package com.example.pharmachek.utils

import android.widget.EditText
import android.widget.Toast
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlin.coroutines.coroutineContext

fun Fragment.navigateTo(direction: Int) {
    findNavController().navigate(direction)
}

fun Fragment.navigateTo(direction: NavDirections) {
    findNavController().navigate(direction)
}

/**
 * This function is responsible for the color of the status bar
 */
fun Fragment.setStatusBarColor(navigationBarColor: Int) {
    requireActivity().window.statusBarColor = requireContext().getColor(navigationBarColor)
}

/**
 * This function is responsible for the color of the navigation bar
 */
fun Fragment.setNavigationBarColor(navigationBarColor: Int) {
    requireActivity().window.navigationBarColor = requireContext().getColor(navigationBarColor)
}

/**
 * If state true, the navigation bar icons is dark.
 * If state false, the navigation bar icons is light
 */
fun Fragment.setLightNavigationBar(state: Boolean) {
    WindowInsetsControllerCompat(
        requireActivity().window,
        requireActivity().window.decorView
    ).isAppearanceLightNavigationBars = state
}

/**
 * If state true, the status bar icons is dark.
 * If state false, the status bar icons is light
 */
fun Fragment.setLightStatusBar(state: Boolean) {
    WindowInsetsControllerCompat(
        requireActivity().window,
        requireActivity().window.decorView
    ).isAppearanceLightStatusBars = state
}

fun Fragment.toastMessage(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.popBackStack() {
    findNavController().popBackStack()
}