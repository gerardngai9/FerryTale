package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentBookingBinding
import kotlinx.android.synthetic.main.fragment_booking.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

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
        binding.root.bookingBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_booking_to_navigation_payment)
        }
        binding.root.bookingBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_booking_to_navigation_payment)
        }
        return binding.root
    }
}
