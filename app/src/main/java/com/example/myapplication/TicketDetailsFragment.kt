package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentTicketDetailsBinding
import kotlinx.android.synthetic.main.fragment_ticket_details.view.*

class TicketDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTicketDetailsBinding>(inflater,
            R.layout.fragment_ticket_details,container,false)
        binding.root.cancelTicketBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_ticketDetails_to_navigation_cancel)
        }
        return binding.root
        }
    }
