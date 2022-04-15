package com.example.cw1786_123

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var activity_name : EditText
    private lateinit var location : EditText
    private lateinit var datePicker : EditText
    private lateinit var time : EditText
    private lateinit var report : EditText
    private lateinit var save: Button

    val myCalendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_name = findViewById(R.id.activity)
        location = findViewById(R.id.location)
        datePicker = findViewById(R.id.date)
        time = findViewById(R.id.time)
        report = findViewById(R.id.reporter)
        save = findViewById(R.id.btn_save)

        val date = OnDateSetListener { view, year, month, day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            updateLabel()
        }

        datePicker.setOnClickListener {
            DatePickerDialog(this@MainActivity, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        time.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this@MainActivity, { timePicker, hourOfDay, minutes ->
                val value = "$hourOfDay:$minutes"
                time.setText(value)
            }, 0, 0, false)
            timePickerDialog.show()
        }

        save.setOnClickListener {
            if (TextUtils.isEmpty(activity_name.text)
                || TextUtils.isEmpty(location.text)
                || TextUtils.isEmpty(datePicker.text)
                || TextUtils.isEmpty(time.text)
                || TextUtils.isEmpty(report.text)) {
                Toast.makeText(this, "Please fill in all field!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        datePicker.setText(dateFormat.format(myCalendar.time))
    }
}