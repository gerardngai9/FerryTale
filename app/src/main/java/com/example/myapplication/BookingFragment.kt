package com.example.myapplication

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.example.myapplication.databinding.FragmentBookingBinding
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
        binding.root.bookingBtn.setOnClickListener {
                bookingDetail()

                /*view: View ->
            view.findNavController().navigate(R.id.action_navigation_booking_to_navigation_payment)*/
        }
        /*binding.root.bookingBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_booking_to_navigation_payment)
        }*/
        return binding.root
    }

    private fun bookingDetail(){

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
            ageText1.error = "Name cannot be Empty"
            ageText1.requestFocus()
            return
        }

        if(nameText2.text.toString().isEmpty()){
            nameText2.error = "Name cannot be Empty"
            nameText2.requestFocus()
            return
        }

        if(ageText2.text.toString().isEmpty()){
            ageText2.error = "Name cannot be Empty"
            ageText2.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailIDText4.text.toString()).matches()) {
            emailIDText4.error = "Please enter valid email"
            emailIDText4.requestFocus()
            return
        }


        val ref = FirebaseDatabase.getInstance().getReference("Passenger")


        val bookingID =ref.push().getKey()
        val user = auth.currentUser
        val uid = user!!.uid

        val user1 = Passenger(uid.toString(),emailIDText4.text.toString(), mobileNoText.text.toString(), nameText1.text.toString(), ageText1.text.toString(), nameText2.text.toString(),ageText2.text.toString())
        ref.child(bookingID.toString()).setValue(user1).addOnCompleteListener{
            Toast.makeText(activity, "Register save successfully", Toast.LENGTH_LONG).show()


            view?.findNavController()?.navigate(R.id.action_navigation_booking_to_navigation_payment)

        }





    }
}
