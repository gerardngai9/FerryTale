package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentEditScheduleBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_edit_schedule.view.*
import kotlinx.android.synthetic.main.fragment_member_account.view.*

class EditScheduleFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEditScheduleBinding>(
            inflater,
            R.layout.fragment_edit_schedule, container, false
        )
        binding.root.insertScheduleBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_StaffeditSchedule_to_navigation_StaffInsertSchedule)
        }
        return binding.root
    }
}