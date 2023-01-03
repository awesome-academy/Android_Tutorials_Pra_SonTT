package com.sun.android.async

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sun.android.databinding.ActivityAsyncTaskBinding

class asyncTaskActivity : AppCompatActivity() {

    private val TEXT_STATE = "currentText"
    val binding by lazy { ActivityAsyncTaskBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            binding.textViewMessage.text = (savedInstanceState.getString(TEXT_STATE))
        }
        binding.buttonAsync.setOnClickListener() {
            binding.textViewMessage.text = "Napping"
            MyAsyncTask(binding.textViewMessage,binding.progressBarAsync).execute()
        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_STATE, binding.textViewMessage.text.toString())
    }
}
