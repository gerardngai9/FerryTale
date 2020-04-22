package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.FragmentRegisterBinding
import android.util.Patterns
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment(){
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(
            inflater,
            R.layout.fragment_register, container, false
        )

        val spinner: Spinner = binding.root.findViewById(R.id.roleSpinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(activity,
            R.array.role_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        binding.root.Signup.setOnClickListener {
            signUpUser()
            //view.findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
        }
        binding.root.button7.setOnClickListener {

            view?.findNavController()?.navigate(R.id.action_navigation_register_to_navigation_login)
        }

        /*roleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

        }*/
        return binding.root
    }

    private fun signUpUser() {

        if (name.text.toString().isEmpty()) {
            name.error = "Name cannot be Empty"
            name.requestFocus()
            return
        }

        if (birthday.text.toString().isEmpty()) {
            birthday.error = "Birthday cannot be Empty"
            birthday.requestFocus()
            return
        }

        if (phone.text.toString().isEmpty()) {
            phone.error = "Phone No cannot be Empty"
            phone.requestFocus()
            return
        }

        if (email.text.toString().isEmpty()) {
            email.error = "Email cannot be Empty"
            email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please enter valid email"
            email.requestFocus()
            return
        }

        if (editText8.text.toString().isEmpty()) {
            editText8.error = "Password cannot be Empty"
            editText8.requestFocus()
            return
        }
        if (editText9.text.toString().isEmpty()) {
            editText9.error = "Please Retype Password"
            editText9.requestFocus()
            return
        }

        if(editText8.text.toString() != editText9.text.toString())
        {
            editText9.error = "Please Enter same password"
            editText9.requestFocus()
            return
        }


        auth.createUserWithEmailAndPassword(email.text.toString(), editText8.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val uid = user!!.uid
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {


                                    val ref = FirebaseDatabase.getInstance().getReference("Users")

                                    val user1 = User(name.text.toString(), phone.text.toString(), birthday.text.toString(), email.text.toString(), roleSpinner.selectedItem.toString())
                                    ref.child(uid).setValue(user1).addOnCompleteListener{
                                        Toast.makeText(activity, "Register save successfully", Toast.LENGTH_LONG).show()

                                    }

                                view?.findNavController()?.navigate(R.id.action_navigation_register_to_navigation_login)
//                                val intent = Intent(activity, LoginFragment::class.java)
//                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                startActivity(intent)
                            }
                        }
                } else {
                    Toast.makeText(
                        activity, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
