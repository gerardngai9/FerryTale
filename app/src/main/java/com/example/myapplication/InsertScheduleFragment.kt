package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentInsertScheduleBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_insert_schedule.view.*
import kotlinx.android.synthetic.main.fragment_member_account.view.*
import kotlinx.android.synthetic.main.fragment_member_account.view.scrollConstraint

class InsertScheduleFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentInsertScheduleBinding>(
            inflater,
            R.layout.fragment_insert_schedule, container, false
        )
        binding.root.ConfirminsertScheduleBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_StaffInsertSchedule_to_navigation_StaffeditSchedule)
        }

        binding.root.CancelinsertScheduleBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_StaffInsertSchedule_to_navigation_StaffeditSchedule)
        }
        return binding.root
    }
}