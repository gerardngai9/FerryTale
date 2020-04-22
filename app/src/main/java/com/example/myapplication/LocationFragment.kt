package com.example.myapplication

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentLocationBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_location.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.location_item_list.*
import kotlinx.android.synthetic.main.fragment_location.*
import android.content.Intent
import android.location.Location
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs


class LocationFragment : Fragment() {

    lateinit var scheduleList: MutableList<Schedule>
    lateinit var ref: DatabaseReference
    lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLocationBinding>(
            inflater,
            R.layout.fragment_location, container, false
        )

        val args: LocationFragmentArgs by navArgs()
        val originDest: String = args.locations

        val locationLV: ListView = binding.root.findViewById(R.id.locationLV)
        locationLV.setOnItemClickListener { parent, view, position, id ->
            lateinit var fromTo: String
            if(originDest.equals("From")){
                fromTo = scheduleList[position].origin
            } else if (originDest.equals("To")){
                fromTo = scheduleList[position].destination
            }
            val action = LocationFragmentDirections.actionNavigationLocationToNavigationHome("$fromTo|$originDest")
            Navigation.findNavController(view).navigate(action)

        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        val args: LocationFragmentArgs by navArgs()
        val originDest: String = args.locations
        var i: Int = 0

        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
        val manager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        scheduleList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Schedule")
        listView = activity!!.findViewById(R.id.locationLV)

        searchView.setSearchableInfo(manager.getSearchableInfo(activity!!.componentName))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchItem.collapseActionView()

                Toast.makeText(context, "$i results found", Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isNotEmpty()){
                    scheduleList.clear()
                    i = 0
                    ref.addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            Toast.makeText(
                                activity, "Database Error.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        override fun onDataChange(p0: DataSnapshot) {
                            val search = newText.toLowerCase()

                            if (p0!!.exists()) {
                                for (h in p0.children) {

                                    val schedule = h.getValue(Schedule::class.java)
                                    if (schedule?.origin.toString().toLowerCase().contains(search)) {
                                        scheduleList.add(schedule!!)
                                        i++
                                    }
                                }
                                val adapter = ScheduleAdapter(
                                    requireContext().applicationContext,
                                    R.layout.location_item_list,
                                    scheduleList, originDest
                                )
                                    listView.adapter = adapter
                            }
                        }
                    })
                } else {
                    scheduleList.clear()
                    i = 0
                    ref.addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            Toast.makeText(
                                activity, "Database Error.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            if (p0!!.exists()) {
                                for (h in p0.children) {
                                    val schedule = h.getValue(Schedule::class.java)
                                    scheduleList.add(schedule!!)
                                    i++
                                }
                                val adapter = ScheduleAdapter(
                                    requireContext().applicationContext,
                                    R.layout.location_item_list,
                                    scheduleList, originDest
                                )
                                listView.adapter = adapter
                            }
                        }
                    })
                }

                Toast.makeText(context, "Looking for $newText", Toast.LENGTH_LONG).show()
                return false
            }
        })
    }
}

