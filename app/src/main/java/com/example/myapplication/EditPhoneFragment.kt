package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentUserEditphonenoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_user_editphoneno.*
import kotlinx.android.synthetic.main.fragment_user_editphoneno.view.*

class EditPhoneFragment: Fragment() {
    var auth =  FirebaseAuth.getInstance()
    private lateinit var ref: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUserEditphonenoBinding>(
            inflater,
            R.layout.fragment_user_editphoneno, container, false
        )

        ref = FirebaseDatabase.getInstance().getReference("Users")
        binding.root.ConfirmEditNameBtn.setOnClickListener {view: View ->
            updatedPhone()
        }
        binding.root.button6.setOnClickListener {view: View ->
            view?.findNavController()?.navigate(R.id.action_navigation_editPhone_to_navigation_profile)
        }


        return binding.root
    }

    private fun updatedPhone(){
        if(editPhone.text.toString().isEmpty()) {
            editPhone.error = "Phone No cannot be Empty"
            editPhone.requestFocus()
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
                    val name = p0.getValue(User::class.java)!!.fullName
                    val username = p0.getValue(User::class.java)!!.username
                    val birthday = p0.getValue(User::class.java)!!.birthDay
                    val role = p0.getValue(User::class.java)!!.userRole

                    val user1 = User(name, editPhone.text.toString().trim(), birthday, username, role)
                    ref.child(auth.currentUser!!.uid).setValue(user1)
                    view?.findNavController()?.navigate(R.id.action_navigation_editPhone_to_navigation_profile)
                }
            })

        }
    }
}