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
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentTicketDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_ticket_details.view.*


class TicketDetailsFragment : Fragment() {

    lateinit var BookingList: MutableList<Booking>
    lateinit var ref: DatabaseReference
    lateinit var listView: ListView

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        val binding = DataBindingUtil.inflate<FragmentTicketDetailsBinding>(
            inflater,
            R.layout.fragment_ticket_details, container, false
        )
        binding.root.cancelTicketBtn.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_navigation_ticketDetails_to_navigation_cancel)
        }

        listView = binding.ticketDetailsLV
        bookingDetail(listView)

        return binding.root
    }

    private fun bookingDetail(listView: ListView) {
        BookingList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Booking")

        val args: TicketDetailsFragmentArgs by navArgs()
        var bookingID: String = args.bookingID

        val user = auth.currentUser
        val uid = user!!.uid

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
                        val booking = h.getValue(Booking::class.java)
                        if(booking!!.bookingID.equals(bookingID.toString())){
                            BookingList.add(booking!!)
                        }
                    }

                    val adapter = BookingAdapter2(
                        requireContext().applicationContext,
                        R.layout.ticket_details_item_list,
                        BookingList
                    )
                    listView.adapter = adapter
                }
            }
        })
    }
}

