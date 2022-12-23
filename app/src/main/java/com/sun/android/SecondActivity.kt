package com.sun.android
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sun.android.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {
    val binding by lazy{ ActivitySecondBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val dataRevice = intent.getStringExtra(EXTRA_REV)
        binding.textViewRevice.setText(dataRevice.toString())
    }
    fun returnReply(view: View?) {
        val reply: String = binding.editTextSecond.text.toString()
        val replyIntent = Intent(this, MainActivity::class.java)
        replyIntent.putExtra(EXTRA_REPLY, reply)
        setResult(RESULT_OK, replyIntent)
        finish()
    }
}

