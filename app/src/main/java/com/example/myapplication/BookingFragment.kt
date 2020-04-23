package com.example.myapplication

import android.app.ActionBar
import android.os.Bundle
import android.text.Layout
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.MATCH_PARENT
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.example.myapplication.databinding.FragmentBookingBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.display_schedule.*
import kotlinx.android.synthetic.main.fragment_booking.*
import kotlinx.android.synthetic.main.fragment_booking.view.*

/**
 * A simple [Fragment] subclass.
 */
class BookingFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        val binding = DataBindingUtil.inflate<FragmentBookingBinding>(
            inflater,
            R.layout.fragment_booking, container, false
        )
        binding.copassengerInfoCardView.visibility = View.INVISIBLE
        binding.seatsText.setText("1")
        var counter: Int = 1

        binding.btnPlus.setOnClickListener() {
            if(counter<10)
                counter++
            binding.seatsText.setText(counter.toString())

            if(binding.seatsText.text.toString().toInt()>1){
                binding.copassengerInfoCardView.visibility = View.VISIBLE
            } else {
                binding.copassengerInfoCardView.visibility = View.INVISIBLE
            }
        }

        binding.btnMinus.setOnClickListener(){
            if(counter>1)
                counter--
            binding.seatsText.setText(counter.toString())

            if(binding.seatsText.text.toString().toInt()>1){
                binding.copassengerInfoCardView.visibility = View.VISIBLE
            } else {
                binding.copassengerInfoCardView.visibility = View.INVISIBLE
            }
        }

        var dataCVV : Long

        binding.root.bookingBtn.setOnClickListener {
            bookingDetail(counter)
            scheduleDetail(counter)
        }
        return binding.root
    }

    private fun bookingDetail(counter: Int){

        if(emailIDText4.text.toString().isEmpty()){
            emailIDText4.error = "Name cannot be Empty"
            emailIDText4.requestFocus()
            return

        }

        if(mobileNoText.text.toString().isEmpty()){
            mobileNoText.error = "Name cannot be Empty"
            mobileNoText.requestFocus()
            return

        }

        if(nameText1.text.toString().isEmpty()){
            nameText1.error = "Name cannot be Empty"
            nameText1.requestFocus()
            return
        }


        if(ageText1.text.toString().isEmpty()){
            ageText1.error = "Age cannot be Empty"
            ageText1.requestFocus()
            return
        }

            /*if(nameText2.text.toString().isEmpty()) {
                nameText2.error = "Name cannot be Empty"
                nameText2.requestFocus()
                return
            }

            if(ageText2.text.toString().isEmpty()){
                ageText2.error = "Age cannot be Empty"
                ageText2.requestFocus()
                return
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(emailIDText4.text.toString()).matches()) {
                emailIDText4.error = "Please enter valid email"
                emailIDText4.requestFocus()
                return
            }*/

        val ref = FirebaseDatabase.getInstance().getReference("Passenger")

        val bookingID =ref.push().getKey()
        val user = auth.currentUser
        val uid = user!!.uid

        val user1 = Passenger(uid.toString(),emailIDText4.text.toString(), mobileNoText.text.toString(), nameText1.text.toString(), ageText1.text.toString(), nameText2.text.toString(),ageText2.text.toString())
        ref.child(bookingID.toString()).setValue(user1).addOnCompleteListener{
            Toast.makeText(activity, "Passenger save successfully", Toast.LENGTH_LONG).show()

        }
    }

    private fun scheduleDetail(counter: Int){
        val args: BookingFragmentArgs by navArgs()
        var scheduleID: String = args.scheduleID

        var ref = FirebaseDatabase.getInstance().getReference("Schedule")
        var ref2 = FirebaseDatabase.getInstance().getReference("Booking")

        val bookingID =ref.push().getKey()
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
                var total: Double = 0.00
                var i: Int = 0
                if (p0!!.exists()) {
                    for (h in p0.children) {
                        val schedule = h.getValue(Schedule::class.java)

                        if(schedule!!.scheduleID.equals(scheduleID)){
                            while(i < counter) {
                                total += schedule.priceAdult
                                i++
                            }
                                if(ageText1.text.toString().isNotEmpty()){
                                    if(Integer.parseInt(ageText1.text.toString()) <= 12){
                                        total-=schedule.priceAdult
                                        total+=schedule.priceChild
                                    }
                                }
                                if(ageText2.text.toString().isNotEmpty()){
                                    if(Integer.parseInt(ageText2.text.toString()) <= 12){
                                        total-=schedule.priceAdult
                                        total+=schedule.priceChild
                                    }
                                }

                            val user1 = Booking(bookingID.toString(), uid.toString(), schedule.origin, schedule.destination, schedule.startTime, counter, total)
                            ref2.child(bookingID.toString()).setValue(user1).addOnCompleteListener{
                                Toast.makeText(activity, "Booking save successfully", Toast.LENGTH_LONG).show()

                                view?.findNavController()?.navigate(R.id.action_navigation_booking_to_navigation_payment)
                        }

                        }
                    }
                }
            }
        })
    }
}
