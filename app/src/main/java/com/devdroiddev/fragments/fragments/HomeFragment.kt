package com.devdroiddev.fragments.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.devdroiddev.fragments.MainActivity
import com.devdroiddev.fragments.R
import com.devdroiddev.fragments.childfragments.FirstFragment
import com.devdroiddev.fragments.childfragments.ForthFragment
import com.devdroiddev.fragments.childfragments.SecondFragment
import com.devdroiddev.fragments.childfragments.ThirdFragment
import com.devdroiddev.fragments.databinding.ActivityMainBinding


class HomeFragment : Fragment() {
    private var count: Int = 0
    private lateinit var binding: ActivityMainBinding
    private val FIRST_FRAGMENT = "first_frag"
    private val SECOND_FRAGMENT = "second_frag"
    private val THIRD_FRAGMENT = "third_frag"
    private val FORTH_FRAGMENT = "forth_frag"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val layout = view.findViewById<LinearLayout>(R.id.text_ll)
        val firstTextView = view.findViewById<TextView>(R.id.first_child_frag)
        val secondTextView = view.findViewById<TextView>(R.id.Second_child_frag)
        val thirdTextView = view.findViewById<TextView>(R.id.Third_child_frag)
        val forthTextView = view.findViewById<TextView>(R.id.Forth_child_frag)

        loadChildFragment(FirstFragment(), FIRST_FRAGMENT)
        firstTextView.setOnClickListener {
            loadChildFragment(FirstFragment(), FIRST_FRAGMENT)
        }

        secondTextView.setOnClickListener {
            loadChildFragment(SecondFragment(), SECOND_FRAGMENT)
        }

        thirdTextView.setOnClickListener {
            loadChildFragment(ThirdFragment(), THIRD_FRAGMENT)
        }

        forthTextView.setOnClickListener {
            loadChildFragment(ForthFragment(), FORTH_FRAGMENT)
        }

        return view
    }

    private fun loadChildFragment(fragment: Fragment, fragmentTag: String) {
        val fragmentTransaction = childFragmentManager.beginTransaction()
        val currentFragment = childFragmentManager.findFragmentByTag(fragmentTag)
        // Log.d(APP_TAG, "${existingFragment.toString()}")

        if (currentFragment != null) {
            Log.d(MainActivity.APP_TAG, "${currentFragment.toString()}")
            // If the fragment already exists, hide all other fragments
            for (otherFragment in childFragmentManager.fragments) {
                if (otherFragment != currentFragment) {
                    Log.d(MainActivity.APP_TAG, "${otherFragment.toString()}")
                    fragmentTransaction.hide(otherFragment)
                }
            }
            fragmentTransaction.show(currentFragment)
        } else {
            // If the fragment does not exist, add it to the container
            fragmentTransaction.add(R.id.child_fragment_container, fragment, fragmentTag)
            Log.d(MainActivity.APP_TAG, "Added $fragmentTag")


        }

        fragmentTransaction.commit()

    }
    /* val btn = view.findViewById<Button>(R.id.increment)
     val txt = view.findViewById<TextView>(R.id.count)*/

  /*       btn.setOnClickListener {
            increment()
            txt.text = count.toString()
        }
        return view
    }
*/
    private fun increment() {
        count++
    }
}
