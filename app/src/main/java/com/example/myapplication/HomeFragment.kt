package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.fromTextField
import kotlinx.android.synthetic.main.fragment_home.view.toTextField

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home, container, false
        )

        binding.root.searchBtn.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_navigation_home_to_navigation_schedule)
        }
        binding.root.fromTextField.setOnClickListener {view: View ->
            val fromText: TextView = binding.root.findViewById(R.id.fromText)
            val from= fromText.text.toString()
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationLocation("$from")
            Navigation.findNavController(view).navigate(action)
        }
        binding.root.toTextField.setOnClickListener {view: View ->
            val toText: TextView = binding.root.findViewById(R.id.toText)
            val to = toText.text.toString()
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationLocation("$to")
            Navigation.findNavController(view).navigate(action)
        }

        val args: HomeFragmentArgs by navArgs()
        var fromTo: String = args.locations2

        if(fromTo != "Default"){
            if(fromTo.split("|")[1].equals("From")) {
                binding.root.fromTextField.setText(fromTo.split("|")[0])
            }
            else if(fromTo.split("|")[1].equals("To")){
                binding.root.toTextField.setText(fromTo.split("|")[0])
            }

        }

        return binding.root
    }
}

