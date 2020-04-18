package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.view.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login, container, false
        )
        binding.root.loginBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
        }
        binding.root.registerBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_login_to_navigation_register)
        }
        return binding.root
    }

}