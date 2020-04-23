package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentCopassengerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_completed.view.*

class CopassengerFragment : Fragment() {

    lateinit var CopassengerList: MutableList<Passenger>
    lateinit var ref: DatabaseReference
    lateinit var listView: ListView

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        val binding = DataBindingUtil.inflate<FragmentCopassengerBinding>(inflater,
            R.layout.fragment_copassenger,container,false)

        listView = binding.copassengerLV
        copassengerDetail(listView)

        return binding.root
    }

    private fun copassengerDetail(listView: ListView){
        CopassengerList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Passenger")

        var user = auth.currentUser
        var uid = user!!.uid

        listView.setOnItemClickListener{ parent, view, position, id ->
            //bookingID = CopassengerList[position].bookingID
            //val action = MyBookingsFragmentDirections.actionNavigationMyBookingsToNavigationTicketDetails("$bookingID")
            //Navigation.findNavController(view).navigate(action)
        }

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

                        val passenger = h.getValue(Passenger::class.java)
                        if(passenger!!.uid.equals(uid.toString())){
                            CopassengerList.add(passenger!!)
                            counter++
                        }
                    }

                    val adapter = CopassengerAdapter(
                        requireContext().applicationContext,
                        R.layout.copassenger_item_list,
                        CopassengerList
                    )
                    listView.adapter = adapter
                }
            }
        })
    }

}
