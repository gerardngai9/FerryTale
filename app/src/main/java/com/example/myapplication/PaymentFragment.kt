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
import com.example.myapplication.databinding.FragmentPaymentBinding
import com.google.firebase.database.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_payment.*
import kotlinx.android.synthetic.main.fragment_payment.view.*
import com.google.firebase.database.*

/**
 * A simple [Fragment] subclass.
 */
class PaymentFragment : Fragment() {
    var auth =  FirebaseAuth.getInstance()
    private lateinit var ref: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        val binding = DataBindingUtil.inflate<FragmentPaymentBinding>(
            inflater, R.layout.fragment_payment, container, false)

        binding.root.Purchase.setOnClickListener {
            payment()
            //view.findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
        }
        ref = FirebaseDatabase.getInstance().getReference( "Bank")

        return binding.root
    }


    private fun payment(){
        if(CVV.text.toString().isEmpty()){
            CVV.error = "CVV cannot be Empty"
            CVV.requestFocus()
            return
        }

        if(ValidDate.text.toString().isEmpty()){
            ValidDate.error = "Valid Date cannot be Empty"
            ValidDate.requestFocus()
            return
        }

        if(editText.text.toString().isEmpty()){
            editText.error = "Account Number cannot be Empty"
            editText.requestFocus()
            return
        }

        var id ="1"



        ref.child("1").addListenerForSingleValueEvent(object :ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                val dataCVV= p0.getValue(Bank::class.java)!!.CVV
                val dataAccNum = p0.getValue(Bank::class.java)!!.accNo
                val dataValidDate = p0.getValue(Bank::class.java)!!.validDate


                if(CVV.text.toString()==dataCVV.toString())
                {
                    if(ValidDate.text.toString().equals(dataValidDate.toString()))
                    {
                        if(editText.text.toString().equals(dataAccNum.toString()))
                        {
                            Toast.makeText(activity, "Payment Successful", Toast.LENGTH_SHORT).show()
                            //view?.findNavController()?.navigate(R.id.action_navigation_login_to_navigation_home)
                        }else
                        {
                            Toast.makeText(activity, "CVV, ValidDate or Accound Number Wrong!", Toast.LENGTH_LONG).show()
                        }
                    }else
                    {
                        Toast.makeText(activity, "CVV, ValidDate or Accound Number Wrong!", Toast.LENGTH_LONG).show()
                    }
                }else
                {
                    Toast.makeText(activity, "CVV, ValidDate or Accound Number Wrong!", Toast.LENGTH_LONG).show()
                }







            }

        })



    }


}


