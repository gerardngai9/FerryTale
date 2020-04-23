package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class BookingAdapter2(val mCtx: Context, val layoutResId: Int, val ticketDetailsList: List<Booking>)
    : ArrayAdapter<Booking>(mCtx, layoutResId, ticketDetailsList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)


        val startTime: TextView = view.findViewById(R.id.startTime)
        val sourceText: TextView = view.findViewById(R.id.sourceText)
        val dest: TextView = view.findViewById(R.id.dest)
        val ticketNo2: TextView = view.findViewById(R.id.ticketNo2)
        val total: TextView = view.findViewById(R.id.total)

        val booking = ticketDetailsList[position]

        startTime.text = booking.startTime.toUpperCase()
        sourceText.text = booking.origin.toUpperCase()
        dest.text = booking.destination.toUpperCase()
        ticketNo2.text = booking.bookingID.toUpperCase()
        total.text = booking.total.toString()

        return view
    }

}