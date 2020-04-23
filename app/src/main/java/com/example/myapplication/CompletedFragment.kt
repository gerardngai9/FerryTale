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
import com.example.myapplication.databinding.FragmentCompletedBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_completed.view.*

class CompletedFragment : Fragment() {

    lateinit var BookingList: MutableList<Booking>
    lateinit var ref: DatabaseReference
    lateinit var listView: ListView

    lateinit var bookingID: String
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        val binding = DataBindingUtil.inflate<FragmentCompletedBinding>(inflater,
            R.layout.fragment_completed,container,false)

        listView = binding.myBookingsLV
        bookingDetail(listView)

        return binding.root
    }

    private fun bookingDetail(listView: ListView){
        BookingList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Booking")

        var user = auth.currentUser
        var uid = user!!.uid

        listView.setOnItemClickListener{ parent, view, position, id ->
            bookingID = BookingList[position].bookingID
            val action = MyBookingsFragmentDirections.actionNavigationMyBookingsToNavigationTicketDetails("$bookingID")
            Navigation.findNavController(view).navigate(action)
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

                        val booking = h.getValue(Booking::class.java)
                        if(booking!!.userID.equals(uid.toString())){
                            BookingList.add(booking!!)
                            counter++
                        }
                    }

                    val adapter = BookingAdapter(
                        requireContext().applicationContext,
                        R.layout.mybookings_item_list,
                        BookingList
                    )
                    listView.adapter = adapter
                }
            }
        })
    }

}
