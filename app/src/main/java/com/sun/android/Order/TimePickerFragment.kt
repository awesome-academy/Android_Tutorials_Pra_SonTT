package com.sun.android.Order

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*
import android.text.format.DateFormat;

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {


    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        (activity as? useDialogActivity)?.let {
            it.processTimePickerResult(hourOfDay =p1, minute =p2)
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c:Calendar = Calendar.getInstance()
        val hour = c[Calendar.HOUR_OF_DAY]
        val minute = c[Calendar.MINUTE]
        return TimePickerDialog(
            activity, this, hour, minute,
            DateFormat.is24HourFormat(getActivity()
            )
        )
    }
}
