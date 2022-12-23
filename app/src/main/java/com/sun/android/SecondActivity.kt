package com.sun.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val data = intent.getStringExtra("key")
        val rev = findViewById<TextView>(R.id.revice)
        rev.setText("$data")
    }
    fun returnReply(view: View?) {
        val mReply = findViewById<EditText>(R.id.editText_second)
        val reply: String = mReply.text.toString()
        val replyIntent = Intent(this, MainActivity::class.java)
        replyIntent.putExtra("reply", reply)

        setResult(RESULT_OK, replyIntent)
        finish()
    }
}
