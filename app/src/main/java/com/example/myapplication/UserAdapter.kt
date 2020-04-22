package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class UserAdapter(val mCtx: Context, val layoutResId: Int, val userList: List<User>)
    : ArrayAdapter<User>(mCtx, layoutResId, userList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val allname: TextView = view.findViewById(R.id.allname)
        val allage: TextView = view.findViewById(R.id.allage)
        val allemail: TextView = view.findViewById(R.id.allemail)
        val allphoneno: TextView = view.findViewById(R.id.allphoneno)

        val user = userList[position]
        Log.i("checking", "uid:a")
        allname.text = user.fullName
        allage.text = user.birthDay
        allemail.text = user.username
        allphoneno.text = user.phone


        return view
    }

}