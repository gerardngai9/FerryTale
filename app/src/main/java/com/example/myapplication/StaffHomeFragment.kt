package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.example.myapplication.databinding.FragmentStaffHomeBinding
class StaffHomeFragment : Fragment() {
    var auth = FirebaseAuth.getInstance()
    private lateinit var ref: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentStaffHomeBinding>(
            inflater,
            R.layout.fragment_staff_home, container, false
        )
        return binding.root
    }
}