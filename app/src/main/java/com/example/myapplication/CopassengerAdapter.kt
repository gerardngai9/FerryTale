package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CopassengerAdapter(val mCtx: Context, val layoutResId: Int, val CopassengerList: List<Passenger>)
    : ArrayAdapter<Passenger>(mCtx, layoutResId, CopassengerList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val nameTV: TextView = view.findViewById(R.id.nameTV)
        val ageTV: TextView = view.findViewById(R.id.ageTV)

        val passenger = CopassengerList[position]

        nameTV.text = passenger.coName
        ageTV.text = passenger.coAge

        return view
    }

}