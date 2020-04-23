package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentMemberAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_member_account.*
import kotlinx.android.synthetic.main.fragment_member_account.view.*

/**
 * A simple [Fragment] subclass.
 */
class MemberAccountFragment : Fragment() {
    var auth =  FirebaseAuth.getInstance()
    private lateinit var ref: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMemberAccountBinding>(
            inflater,
            R.layout.fragment_member_account, container, false
        )
        ref = FirebaseDatabase.getInstance().getReference( "Users")
        val user = auth.currentUser
        if(user!= null) {
            val uid = user!!.uid
            ref.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    Log.i("checking", "uid: $uid")
                    mobileNo.text = p0.getValue(User::class.java)!!.phone
                    gmail.text = p0.getValue(User::class.java)!!.username

                }
            })

        }
        binding.root.myAccountCardView.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_member_account_to_navigation_profile)
        }
        binding.root.myBookingsCardView.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_member_account_to_navigation_myBookings)
        }
        binding.root.copassengerCardView.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_member_account_to_navigation_copassenger)
        }
        binding.root.contactCardView.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_member_account_to_navigation_contactUs)
        }
        binding.root.settingsCardView.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_member_account_to_navigation_preference)
        }
        binding.root.logoutCardView.setOnClickListener {view: View ->
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        updatedProfile()

        return binding.root
    }
    private fun updatedProfile(){

    }
}

