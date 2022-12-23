package com.sun.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var txtRev: TextView
    private lateinit var text_header: TextView

    val getAct = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        Log.d("tag","weewewe")
        if (it.resultCode == Activity.RESULT_OK) {
            var string = it?.data?.extras?.get("reply")
            text_header.setVisibility(View.VISIBLE)
            txtRev.setVisibility(View.VISIBLE)
            txtRev.setText(string.toString())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtRev =  findViewById(R.id.text_header_reply)
        text_header = findViewById(R.id.text_header)
    }
    fun launchSecondActivity(view: View?) {
        val txtSend = findViewById<EditText>(R.id.edtSend)
        val intent = Intent(this, SecondActivity::class.java)
        val message: String = txtSend.getText().toString()
        intent.putExtra("key", message)
        txtSend.text = null
        getAct.launch(intent )

    }
}
