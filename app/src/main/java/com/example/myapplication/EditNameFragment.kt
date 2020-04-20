package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentUserEditnameBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_member_account.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_user_editname.*
import kotlinx.android.synthetic.main.fragment_user_editname.view.*

class EditNameFragment:Fragment() {
    var auth =  FirebaseAuth.getInstance()
    private lateinit var ref: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUserEditnameBinding>(
        inflater,
        R.layout.fragment_user_editname, container, false
        )
        ref = FirebaseDatabase.getInstance().getReference("Users")
        binding.root.ConfirmEditNameBtn.setOnClickListener {view: View ->
            updatedName()
        }
        binding.root.button6.setOnClickListener {view: View ->
            view?.findNavController()?.navigate(R.id.action_navigation_editUserName_to_navigation_profile)
        }

        return binding.root
    }
    private fun updatedName(){
        if (editName.text.toString().isEmpty()) {
            editName.error = "Name cannot be Empty"
            editName.requestFocus()
            return
        }
        val user = auth.currentUser
        if(user!= null) {
            val uid = user!!.uid
            ref.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    Log.i("checking", "uid: $uid")
                    val phone = p0.getValue(User::class.java)!!.phone
                    val username = p0.getValue(User::class.java)!!.username
                    val birthday = p0.getValue(User::class.java)!!.birthDay
                    val role = p0.getValue(User::class.java)!!.userRole

                    val user1 = User(editName.text.toString().trim(), phone, birthday, username, role)
                    ref.child(auth.currentUser!!.uid).setValue(user1)
                    view?.findNavController()?.navigate(R.id.action_navigation_editUserName_to_navigation_profile)
                }
            })

        }
    }
}