package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentCompletedBinding
import kotlinx.android.synthetic.main.fragment_completed.view.*


class CompletedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCompletedBinding>(inflater,
            R.layout.fragment_completed,container,false)

        binding.root.myBookingCardView.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_myBookings_to_navigation_ticketDetails)
        }

        return binding.root
    }

}
