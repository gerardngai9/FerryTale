package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentAllUserListViewBinding
import com.google.firebase.database.*

class ViewUserFragment : Fragment() {
    lateinit var userList: MutableList<User>
    lateinit var ref: DatabaseReference
    lateinit var listView: ListView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAllUserListViewBinding>(inflater,
            R.layout.fragment_all_user_list_view,container,false)
        userList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Users")
        listView = binding.allUserListView
        ref.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                Log.i("checking", "uid:")
                Toast.makeText(
                    activity, "Database Error.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    Log.i("checking", "uid:")
                    for (h in p0.children) {

                                val user = h.getValue(User::class.java)
                                userList.add(user!!)
                        }
                    }
                    val adapter = UserAdapter(
                        requireContext().applicationContext,
                        R.layout.fragment_all_user_card_view,
                        userList
                    )
                    listView.adapter = adapter

            }
        })


        return binding.root
    }
}