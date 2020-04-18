package com.example.myapplication

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class CalendarDatePicker : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_profile)

            val EditBirthdayBtn = findViewById<Button>(R.id.editBirthDateBtn)
            val EditBirthday     = findViewById<TextView>(R.id.editBirthDate)

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            EditBirthdayBtn.setOnClickListener {

                val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    // Display Selected date in TextView
                    EditBirthday.setText("" + dayOfMonth + " " + month + ", " + year)
                }, year, month, day)
                dpd.show()

            }


            }
}


