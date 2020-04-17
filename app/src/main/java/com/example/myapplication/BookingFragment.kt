package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.FragmentBookingBinding

/**
 * A simple [Fragment] subclass.
 */
class BookingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentBookingBinding>(
            inflater,
            R.layout.fragment_booking, container, false
        )

        return binding.root
    }
}
