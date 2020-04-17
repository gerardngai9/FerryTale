package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.FragmentIntroBinding

/**
 * A simple [Fragment] subclass.
 */
class IntroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentIntroBinding>(
            inflater,
            R.layout.fragment_Intro, container, false
        )

        fun DesButtonClick1(v: View?) {
            val tv = binding.root.findViewById(R.id.textView6) as TextView
            tv.text = "“Why can’t we have a cheaper way to our location?”\n" +
                    "“Can’t we give people better working conditions?”\n" +
                    "“What if we could make life just a little bit easier?" +
                    "And this app is your answer.”"

        }

        fun DesButtonClick2(v: View?) {
            val tv = binding.root.findViewById(R.id.textView6) as TextView
            tv.text = "Variety choices can me made. Book your Ferry tickets from a wide list of your favourite ferry companies"

        }

        fun DesButtonClick3(v: View?) {
            val tv = binding.root.findViewById(R.id.textView6) as TextView
            tv.text = "Good things happen when people can move, whether across town or toward their dreams.  What started as a way to tap a button to get a ride has led to billions of moments of human connection as people around the world go all kinds of places in all kinds of ways with the help of our technology."
        }

        fun DesButtonClick4(v: View?) {
            val tv = binding.root.findViewById(R.id.textView6) as TextView
            tv.text = "We partner with safety advocates and develop new technologies and systems to help improve safety and help make it easier for everyone to get around."
        }

        return binding.root
    }
}
