package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_member_account.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    var auth =  FirebaseAuth.getInstance()
    private lateinit var ref: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentProfileBinding>(
            inflater,
            R.layout.fragment_profile, container, false
        )
        ref = FirebaseDatabase.getInstance().getReference( "Users")
        val user = auth.currentUser
        if(user!=null) {
            val uid = user!!.uid
            ref.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {

                    val testing = p0.getValue(User::class.java)!!.birthDay
                    Log.i("checking", "uid: $testing")
                    editName.setText(p0.getValue(User::class.java)!!.fullName)
                    editBirthDate.setText(p0.getValue(User::class.java)!!.birthDay)
                    editPhone.setText(p0.getValue(User::class.java)!!.phone)
                    editEmail.setText(p0.getValue(User::class.java)!!.username)
                }
            })
        }
        binding.root.editNameBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_profile_to_navigation_editUserName)
        }
        binding.root.editPasswordBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_profile_to_navigation_editPassword)
        }
        binding.root.editPhoneBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_profile_to_navigation_editPhone)
        }
        binding.root.submitBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_profile_to_navigation_member_account)
        }
        binding.root.backBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_profile_to_navigation_member_account)
        }
        return binding.root
    }

}
