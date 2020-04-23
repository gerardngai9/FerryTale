package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ScheduleAdapter(val mCtx: Context, val layoutResId: Int, val scheduleList: List<Schedule>, val originDest: String)
    : ArrayAdapter<Schedule>(mCtx, layoutResId, scheduleList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val originDestText: TextView = view.findViewById(R.id.originDestTV)

        val schedule = scheduleList[position]

            if(originDest.toString().equals("From")){
                originDestText.text = schedule.origin
            } else if(originDest.toString().equals("To")) {
                originDestText.text = schedule.destination
            }

        return view
    }

}