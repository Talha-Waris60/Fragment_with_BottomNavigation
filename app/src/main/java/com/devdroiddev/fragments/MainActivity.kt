package com.devdroiddev.fragments

import android.icu.util.ULocale.ROOT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.sax.RootElement
import android.util.Log
import androidx.fragment.app.Fragment
import com.devdroiddev.fragments.databinding.ActivityMainBinding
import com.devdroiddev.fragments.fragments.CallFragment
import com.devdroiddev.fragments.fragments.HomeFragment
import com.devdroiddev.fragments.fragments.ProfileFragment
import com.devdroiddev.fragments.fragments.SettingFragment
import java.util.Stack

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val HOME_FRAGMENT = "home_frag"
    private val CALL_FRAGMENT = "call_frag"
    private val SETTING_FRAGMENT = "setting_frag"
    private val PROFILE_FRAGMENT = "profile_frag"
    companion object {
        const val APP_TAG = "Fragment_App"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load Fragment For First time
        loadFragment(HomeFragment(), HOME_FRAGMENT)

        // clickListener on bottomNavigation Items & load corresponding fragment
        binding.bottomNavigation.setOnItemSelectedListener { MenuItem ->
            // Performs Check on Ids of MenuItem
            when (MenuItem.itemId) {
                R.id.nav_item_home -> loadFragment(HomeFragment(), HOME_FRAGMENT)
                R.id.nav_item_call -> loadFragment(CallFragment(), CALL_FRAGMENT)
                R.id.nav_item_setting -> loadFragment(SettingFragment(), SETTING_FRAGMENT)
                R.id.nav_item_profile -> loadFragment(ProfileFragment(), PROFILE_FRAGMENT)
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment, fragmentTag: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val desiredFragment = supportFragmentManager.findFragmentByTag(fragmentTag)
        if (desiredFragment != null) {
            Log.d(APP_TAG, "${desiredFragment.toString()}")
            // If the fragment already exists, hide all other fragments
            for (otherFragment in supportFragmentManager.fragments) {
                if (otherFragment != desiredFragment) {
                    Log.d(APP_TAG, "${otherFragment.toString()}")
                    fragmentTransaction.hide(otherFragment)
                }
            }
            fragmentTransaction.show(desiredFragment)
        } else {
            // If the fragment does not exist, add it to the container
            fragmentTransaction.add(R.id.fragment_container, fragment, fragmentTag)
            Log.d(APP_TAG, "Added $fragmentTag")
        }
        fragmentTransaction.commit()
    }
}

/*
private fun loadFragment(fragment: Fragment, fragmentTag: String) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    val existingFragment = supportFragmentManager.findFragmentByTag(fragmentTag)
    Log.d(APP_TAG, "${existingFragment.toString()}")
    if (existingFragment != null) {
        fragmentTransaction.show(existingFragment)
    } else {
        fragmentTransaction.add(R.id.fragment_container, fragment, fragmentTag)
        Log.d(APP_TAG, "$fragmentTag")
    }
    fragmentTransaction.commit()
}
}
*/

    // Get home onBackPressed
/*    override fun onBackPressed() {
          if (binding.bottomNavigation.selectedItemId == R.id.nav_item_home) {
              Log.d(APP_TAG, "If")
              super.onBackPressed()
              finish()
          } else {
              Log.d(APP_TAG, "Else")
              binding.bottomNavigation.selectedItemId = R.id.nav_item_home

          }
    }*/
