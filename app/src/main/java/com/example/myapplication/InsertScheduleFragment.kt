package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentInsertScheduleBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_insert_schedule.*
import kotlinx.android.synthetic.main.fragment_insert_schedule.view.*
import kotlinx.android.synthetic.main.fragment_member_account.view.*
import kotlinx.android.synthetic.main.fragment_member_account.view.scrollConstraint

class InsertScheduleFragment : Fragment() {
    var auth = FirebaseAuth.getInstance()

    private lateinit var ref: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentInsertScheduleBinding>(
            inflater,
            R.layout.fragment_insert_schedule, container, false
        )
        ref = FirebaseDatabase.getInstance().getReference("Schedule")
        binding.root.ConfirminsertScheduleBtn.setOnClickListener { view: View ->
            UpdatedSchedule()

        }

        binding.root.CancelinsertScheduleBtn.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_navigation_StaffInsertSchedule_to_navigation_StaffeditSchedule)
        }
        return binding.root
    }

    private fun UpdatedSchedule() {
        if (insertdestination.text.toString().isEmpty()) {
            insertdestination.error = "Please Enter Destination"
            insertdestination.requestFocus()
            return
        }
        if (insertorigin.text.toString().isEmpty()) {
            insertorigin.error = "Please Enter Origin"
            insertorigin.requestFocus()
            return
        }
        if (insertScheduleDuration.text.toString().isEmpty()) {
            insertScheduleDuration.error = "Please Enter Duration"
            insertScheduleDuration.requestFocus()
            return
        }
        if (editText4.text.toString().isEmpty()) {
            editText4.error = "Please Enter the Price Adult"
            editText4.requestFocus()
            return
        }
        if (editText5.text.toString().isEmpty()) {
            editText5.error = "Please Enter the Price Child"
            editText5.requestFocus()
            return
        }
        if (contactInfoEditText.text.toString().isEmpty()) {
            contactInfoEditText.error = "Please Enter The Starting Time"
            contactInfoEditText.requestFocus()
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("Schedule")
        val scheduleID = ref.push().key
        val schedule1 = Schedule(
            scheduleID.toString(),
            insertdestination.text.toString(),
            insertorigin.text.toString(),
            insertScheduleDuration.text.toString(),
            editText4.text.toString().toDouble(),
            editText5.text.toString().toDouble(),
            contactInfoEditText.text.toString()
        )
        ref.child(scheduleID.toString()).setValue(schedule1).addOnCompleteListener {
            Toast.makeText(activity, "Schedule save successfully", Toast.LENGTH_LONG).show()
            view?.findNavController()
                ?.navigate(R.id.action_navigation_StaffInsertSchedule_to_navigation_StaffeditSchedule)
        }
    }
}