package com.example.myapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentUserEditpasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_user_editpassword.*
import kotlinx.android.synthetic.main.fragment_user_editpassword.view.*

class EditPasswordFragment : Fragment() {
    var auth =  FirebaseAuth.getInstance()
    private lateinit var ref: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUserEditpasswordBinding>(
            inflater,
            R.layout.fragment_user_editpassword, container, false
        )
        ref = FirebaseDatabase.getInstance().getReference( "Users")

        binding.root.ConfirmEditNameBtn.setOnClickListener { view: View ->
            changePass()
        }
        binding.root.button6.setOnClickListener {view: View ->
            view?.findNavController()?.navigate(R.id.action_navigation_editPassword_to_navigation_profile)
        }
        return binding.root
    }
    private fun changePass(){
        if (editPass.text.toString().isEmpty()) {
            editPass.error = "Password cannot be Empty"
            editPass.requestFocus()
            return
        }
        if (editRePass.text.toString().isEmpty()) {
            editRePass.error = "Please Retype Password"
            editRePass.requestFocus()
            return
        }

        if(editPass.text.toString() != editRePass.text.toString())
        {
            editRePass.error = "Please Enter same password"
            editRePass.requestFocus()
            return
        }
        else {
            val user = FirebaseAuth.getInstance().currentUser
            Log.i(TAG, "User password .")

            if (user != null) {
                user.updatePassword(editPass.text.toString())
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "User password updated.")
                            view?.findNavController()
                                ?.navigate(R.id.action_navigation_editPassword_to_navigation_profile)
                        }
                    }
            }
        }

    }
}