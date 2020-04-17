package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentMemberAccountBinding
import kotlinx.android.synthetic.main.fragment_member_account.view.*

/**
 * A simple [Fragment] subclass.
 */
class MemberAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMemberAccountBinding>(
            inflater,
            R.layout.fragment_member_account, container, false
        )
        binding.root.myBookingsCardView.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_member_account_to_navigation_myBookings)
        }
        binding.root.myAccountCardView.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_member_account_to_navigation_profile)
        }
        return binding.root
    }
}

