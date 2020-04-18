package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentProfileBinding
import kotlinx.android.synthetic.main.fragment_member_account.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentProfileBinding>(
            inflater,
            R.layout.fragment_profile, container, false
        )
        binding.root.submitBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_profile_to_navigation_member_account)
        }
        binding.root.backBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_profile_to_navigation_member_account)
        }
        return binding.root
    }

}
