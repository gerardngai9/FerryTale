package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentEditScheduleBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_edit_schedule.*
import kotlinx.android.synthetic.main.fragment_edit_schedule.view.*

class EditScheduleFragment : Fragment(){
    lateinit var scheduleList1: MutableList<Schedule>
    lateinit var ref: DatabaseReference
    lateinit var listView1: ListView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEditScheduleBinding>(
            inflater,
            R.layout.fragment_edit_schedule, container, false
        )
        scheduleList1 = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Schedule")
        listView1 = binding.display

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(
                    activity, "Database Error.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onDataChange(p0: DataSnapshot) {
                var counter : Int = 0
                if (p0!!.exists()) {
                    for (h in p0.children) {
                        val schedule = h.getValue(Schedule::class.java)
                        scheduleList1.add(schedule!!)
                        counter++
                    }
                    binding.totalEntries.text = counter.toString()
                    val adapter = ScheduleAdapter1(
                        requireContext().applicationContext,
                        R.layout.display_schedule,
                        scheduleList1
                    )
                    listView1.adapter = adapter
                }
            }
        })
        binding.root.insertScheduleBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_StaffeditSchedule_to_navigation_StaffInsertSchedule)
        }
        return binding.root
    }
}