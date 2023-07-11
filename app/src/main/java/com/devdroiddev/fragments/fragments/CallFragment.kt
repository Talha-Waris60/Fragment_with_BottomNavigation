package com.devdroiddev.fragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.devdroiddev.fragments.R


class CallFragment : Fragment() {
    private var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_call, container, false)
        val btn = view.findViewById<Button>(R.id.increment)
        val txt = view.findViewById<TextView>(R.id.count)

        btn.setOnClickListener {
            increment()
            txt.text = count.toString()
        }
        return view
    }

    private fun increment() {
        count++
    }

}