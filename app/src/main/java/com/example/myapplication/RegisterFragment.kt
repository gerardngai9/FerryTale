package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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

        /*roleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

        }*/
        return binding.root
    }
}
