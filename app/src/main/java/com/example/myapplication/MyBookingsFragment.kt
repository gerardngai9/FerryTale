package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_completed.*
import com.example.myapplication.SectionsPagerAdapter
import com.example.myapplication.databinding.FragmentMyBookingsBinding
import kotlinx.android.synthetic.main.fragment_completed.view.*
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth


class MyBookingsFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMyBookingsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_my_bookings, container, false)

        val fragmentStateAdapter = SectionsPagerAdapter(requireContext(), childFragmentManager)
        val viewPager : ViewPager = binding.root.findViewById(R.id.myBookingsViewPager)
        viewPager.adapter = fragmentStateAdapter
        val tabs: TabLayout = binding.root.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}

