package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class BookingAdapter(val mCtx: Context, val layoutResId: Int, val bookingList: List<Booking>)
    : ArrayAdapter<Booking>(mCtx, layoutResId, bookingList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)


        val sourceText: TextView = view.findViewById(R.id.sourceText)
        val destText: TextView = view.findViewById(R.id.destText)
        val startTimeText: TextView = view.findViewById(R.id.startTimeText)

        val booking = bookingList[position]

        startTimeText.text = booking.startTime
        sourceText.text = booking.origin
        destText.text = booking.destination

        return view
    }

}