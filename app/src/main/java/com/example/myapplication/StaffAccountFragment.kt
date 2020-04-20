package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentStaffAccountBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_member_account.view.*

/**
 * A simple [Fragment] subclass.
 */
class StaffAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentStaffAccountBinding>(
            inflater,
            R.layout.fragment_staff_account, container, false
        )
        binding.root.myBookingsCardView.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_StaffAccount_to_navigation_StaffviewAllBooking)
        }
        binding.root.myAccountCardView.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_StaffAccount_to_navigation_StaffeditSchedule)
        }
        binding.root.settingsCardView.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_StaffAccount_to_navigation_StaffviewAllUser)
        }
        binding.root.logoutCardView.setOnClickListener {view: View ->
            FirebaseAuth.getInstance().signOut()
            view.findNavController().navigate(R.id.action_navigation_StaffAccount_to_navigation_login)
        }
        return binding.root
    }
}

