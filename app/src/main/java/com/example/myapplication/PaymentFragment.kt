package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.FragmentPaymentBinding

/**
 * A simple [Fragment] subclass.
 */
class PaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPaymentBinding>(
            inflater,
            R.layout.fragment_payment, container, false
        )
        return binding.root
    }
}

