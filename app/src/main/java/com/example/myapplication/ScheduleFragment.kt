package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentScheduleBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import kotlinx.android.synthetic.main.schedule_item_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment() {

    lateinit var scheduleList: MutableList<Schedule>
    lateinit var ref: DatabaseReference
    lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentScheduleBinding>(
            inflater,
            R.layout.fragment_schedule, container, false
        )

        binding.ScheduleLV.setOnItemClickListener{ parent, view, position, id ->
            view.findNavController().navigate(R.id.action_navigation_schedule_to_navigation_booking)
        }

        scheduleList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Schedule")
        listView = binding.ScheduleLV

        val args: ScheduleFragmentArgs by navArgs()
        var from: String = args.locations3.split("|")[0]
        var to: String = args.locations3.split("|")[1]

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(
                    activity, "Database Error.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                var counter: Int = 0
                if (p0!!.exists()) {
                    for (h in p0.children) {
                        val schedule = h.getValue(Schedule::class.java)
                        if(schedule!!.origin.equals(from) && schedule!!.destination.equals(to)){
                            scheduleList.add(schedule!!)
                            counter++
                        }
                        Toast.makeText(
                            activity, "$from|$to",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    binding.totalEntries.text = counter.toString()
                    val adapter = ScheduleAdapter2(
                        requireContext().applicationContext,
                        R.layout.schedule_item_list,
                        scheduleList
                    )
                    listView.adapter = adapter
                }
            }
        })
        return binding.root
    }
}

