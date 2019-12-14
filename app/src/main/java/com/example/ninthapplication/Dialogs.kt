package com.example.ninthapplication

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*


class DatePickerFragment : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    interface OnDateSelectedListener {
        fun onDateSelected(year: Int, month: Int, date: Int)
    }

    private var listener: OnDateSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is OnDateSelectedListener -> listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val date = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(), this, year, month, date)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener?.onDateSelected(year, month, dayOfMonth)
    }
}

class TimeBeginPickerFragment : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {
    interface OnTimeBeginSelectedListener {
        fun onTimeBeginSelected(hourOfDay: Int, minute: Int)
    }

    private var listener: OnTimeBeginSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is OnTimeBeginSelectedListener -> listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(context, this, hour, minute, true)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        listener?.onTimeBeginSelected(hourOfDay, minute)
    }
}

class TimeFinishPickerFragment : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {
    interface OnTimeFinishSelectedListener {
        fun onTimeFinishSelected(hourOfDay: Int, minute: Int)
    }

    private var listener: OnTimeFinishSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is OnTimeFinishSelectedListener -> listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(context, this, hour, minute, true)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        listener?.onTimeFinishSelected(hourOfDay, minute)
    }
}