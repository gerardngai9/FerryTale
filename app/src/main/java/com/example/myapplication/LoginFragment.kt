package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import com.google.firebase.database.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    var auth =  FirebaseAuth.getInstance()
    private lateinit var ref: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login, container, false
        )
        binding.root.loginBtn.setOnClickListener {
            doLogin()
            //view.findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
        }
        binding.root.registerBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_login_to_navigation_register)
        }
        // Initialize Database
        ref = FirebaseDatabase.getInstance().getReference( "Users")


        return binding.root
    }
    private fun doLogin(){
        if(username.text.toString().isEmpty()){
            username.error = "Email cannot be Empty"
            username.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
            username.error = "Please enter valid email"
            username.requestFocus()
            return
        }

        if(password.text.toString().isEmpty()){
            password.error = "Password cannot be Empty"
            password.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(username.text.toString(), password.text.toString()).addOnCompleteListener{
            task -> if(task.isSuccessful){
            val user = auth.currentUser

            updateUI(user)
        }else{
            updateUI(null)
        }
        }


    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentuser: FirebaseUser?) {



        if (currentuser != null) {
            val uid = currentuser!!.uid
            Log.i("checking", "uid: $uid")
            if(currentuser.isEmailVerified) {

                ref.child(uid).addListenerForSingleValueEvent(object :ValueEventListener{

                    override fun onCancelled(p0: DatabaseError) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        Log.i("checking", "uid: $uid")

                        val userRole1= p0.getValue(User::class.java)!!.userRole

                       Log.i("checking", "Role: $userRole1")
                       if(userRole1.equals("Member")){


                           val intent = Intent(activity, UserActivity::class.java)
                           intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                           startActivity(intent)
                            Toast.makeText(activity, "Login Successful. Welcome", Toast.LENGTH_SHORT).show()
                        }
                      else{
                           val intent = Intent(activity, StaffActivity::class.java)
                           intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                           startActivity(intent)
                           //view?.findNavController()?.navigate(R.id.action_navigation_login_to_navigation_home)
                           Toast.makeText(activity, "Login Successful. Welcome Staff", Toast.LENGTH_SHORT).show()
                        }
                  }
                })


                //Test




            }else{
                Toast.makeText(
                    activity, "Please verify your email address.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(activity, "Login failed.", Toast.LENGTH_SHORT).show()
        }
    }

}
