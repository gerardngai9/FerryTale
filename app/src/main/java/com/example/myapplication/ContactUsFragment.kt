package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.FragmentContactUsBinding

/**
 * A simple [Fragment] subclass.
 */
class ContactUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentContactUsBinding>(
            inflater,
            R.layout.fragment_contact_us, container, false
        )

        return binding.root
    }
}

