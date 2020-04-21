package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ScheduleAdapter1(val mCtx: Context, val layoutResId: Int, val scheduleList: List<Schedule>)
    : ArrayAdapter<Schedule>(mCtx, layoutResId, scheduleList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val contactInfoText: TextView = view.findViewById(R.id.contactInfoText)
        val sourceText: TextView = view.findViewById(R.id.sourceText)
        val destText: TextView = view.findViewById(R.id.destText)
        val adPrice: TextView = view.findViewById(R.id.adPrice)
        val chPrice: TextView = view.findViewById(R.id.chPrice)
        val schedule = scheduleList[position]

        contactInfoText.text = schedule.startTime
        sourceText.text = schedule.origin
        destText.text = schedule.destination
        adPrice.text = schedule.priceAdult.toString()
        chPrice.text = schedule.priceChild.toString()

        return view
    }

}