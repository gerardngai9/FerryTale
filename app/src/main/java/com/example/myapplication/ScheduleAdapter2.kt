package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ScheduleAdapter2(val mCtx: Context, val layoutResId: Int, val ScheduleList: List<Schedule>)
    : ArrayAdapter<Schedule>(mCtx, layoutResId, ScheduleList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val timeText: TextView = view.findViewById(R.id.timeText)
        val adPrice: TextView = view.findViewById(R.id.adPrice)
        val chPrice: TextView = view.findViewById(R.id.chPrice)
        val seatQty: TextView = view.findViewById(R.id.seatQty)
        val hrDuration: TextView = view.findViewById(R.id.hrDuration)
        val sourceText: TextView = view.findViewById(R.id.sourceText)
        val destText: TextView = view.findViewById(R.id.destText)

        val schedule = ScheduleList[position]

        timeText.text = schedule.startTime
        adPrice.text = schedule.priceAdult.toString()
        chPrice.text = schedule.priceChild.toString()
        seatQty.text = schedule.seat.toString()
        hrDuration.text = schedule.duration
        sourceText.text = schedule.origin
        destText.text = schedule.destination

        return view
    }

}