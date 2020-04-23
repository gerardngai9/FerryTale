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
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentScheduleBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.schedule_item_list.view.*
import org.w3c.dom.Text

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment() {

    lateinit var ScheduleList: MutableList<Schedule>
    lateinit var ref: DatabaseReference
    lateinit var listView: ListView

    lateinit var scheduleID: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentScheduleBinding>(
            inflater,
            R.layout.fragment_schedule, container, false
        )

        listView = binding.ScheduleLV

        listView.setOnItemClickListener{ parent, view, position, id ->
            scheduleID = ScheduleList[position].scheduleID
            val action = ScheduleFragmentDirections.actionNavigationScheduleToNavigationBooking("$scheduleID")
            Navigation.findNavController(view).navigate(action)
        }

        scheduleDetail(listView)

        return binding.root
    }

    private fun scheduleDetail(listView: ListView){

        val args: ScheduleFragmentArgs by navArgs()
        var from: String = args.locations3.split("|")[0]
        var to: String = args.locations3.split("|")[1]

        ScheduleList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Schedule")

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
                        if (from.isNotEmpty() && to.isNotEmpty()) {
                            if (schedule!!.origin.equals(from) && schedule!!.destination.equals(to)) {
                                ScheduleList.add(schedule!!)
                                counter++
                            }
                        } else if (from.isNotEmpty() && to.isEmpty()) {
                            if (schedule!!.origin.equals(from)) {
                                ScheduleList.add(schedule!!)
                                counter++
                            }
                        } else if (from.isEmpty() && to.isEmpty()){
                            ScheduleList.add(schedule!!)
                            counter++
                        }
                    }
                    totalEntries.text = counter.toString()
                    val adapter = ScheduleAdapter2(
                        requireContext().applicationContext,
                        R.layout.schedule_item_list,
                        ScheduleList
                    )
                    listView.adapter = adapter
                }
            }
        })
    }
}

