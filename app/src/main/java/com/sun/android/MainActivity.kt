package com.sun.android
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sun.android.databinding.ActivityMainBinding

const val EXTRA_REPLY = "reply"
const val EXTRA_REV = "key"

class MainActivity : AppCompatActivity() {
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    val getAct = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK) {
            var dataRevice = it?.data?.extras?.get(EXTRA_REPLY)
            binding.textViewHeader.visibility = View.VISIBLE
            binding.textViewReply.visibility = View.VISIBLE
            binding.textViewReply.text = dataRevice.toString()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

    }
    fun launchSecondActivity(view: View?)  {
        val txtSend = binding.edtSend.text.toString()
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(EXTRA_REV, txtSend)
        binding.textViewReply.visibility = View.INVISIBLE
        getAct.launch(intent)
    }
}



