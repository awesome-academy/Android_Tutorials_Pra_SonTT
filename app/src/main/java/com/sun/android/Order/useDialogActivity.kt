package com.sun.android.Order

import android.R
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.sun.android.databinding.ActivityUseDialogBinding


class useDialogActivity : AppCompatActivity() {
    val binding by lazy { ActivityUseDialogBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonTime.setOnClickListener() {
            val newFragment: DialogFragment = TimePickerFragment()
            newFragment.show(
                supportFragmentManager,
                "time"
            )
        }

        binding.buttonDate.setOnClickListener() {
            val datePickerFragment: DialogFragment = DatePickerFragment()
            datePickerFragment.show(supportFragmentManager, "datePicker")

        }

        binding.buttonAlert.setOnClickListener() {
            val myAlertBuilder = AlertDialog.Builder(this)
            with(myAlertBuilder) {
                setTitle("Alert")
                setMessage("Click OK to continue, or Cancel to stop:")
                setPositiveButton("Proceed", DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(
                        getApplicationContext(), "Pressed OK",
                        Toast.LENGTH_SHORT
                    ).show();
                })
                setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(
                        getApplicationContext(), "Pressed Cancel",
                        Toast.LENGTH_SHORT
                    ).show();
                })
                create()
                show()
            }
        }

    }

    fun processDatePickerResult(year: Int, month: Int, day: Int) {
        val dateMessage = "Date: $month/$day/$year"
        Toast.makeText(
            this, dateMessage,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun processTimePickerResult(hourOfDay: Int, minute: Int) {
        val timeMessage = "$hourOfDay:$minute"
        Toast.makeText(
            this, "time: "
                + timeMessage, Toast.LENGTH_SHORT
        ).show()
    }

}

