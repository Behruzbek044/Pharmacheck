package com.example.pharmachek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pharmachek.databinding.ActivityMainBinding
import com.example.pharmachek.fragments.bottom_navigation_fragments.CatalogFragment
import com.example.pharmachek.fragments.bottom_navigation_fragments.HomeFragment
import com.example.pharmachek.fragments.bottom_navigation_fragments.SavedFragment
import com.example.pharmachek.fragments.bottom_navigation_fragments.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.apply {

            bottomNavigation.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.home -> replaceFragment(HomeFragment())

                    R.id.saved -> replaceFragment(SavedFragment())

                    R.id.catalog -> replaceFragment(CatalogFragment())

                    R.id.settings -> replaceFragment(SettingsFragment())

                    else -> replaceFragment(HomeFragment())
                }
                true
            }
        }
    }


    fun hideBottomNavigation() {
        binding.bottomNavigation.visibility = View.GONE
    }

    fun showBottomNavigation() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container,fragment).commit()
    }

}