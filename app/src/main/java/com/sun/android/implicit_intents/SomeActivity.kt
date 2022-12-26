package com.sun.android.implicit_intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.Toast
import com.sun.android.R
import com.sun.android.databinding.ActivitySomeBinding

const val googleMapPackage = "com.google.android.apps.maps"
const val invalid = "invalid "
class SomeActivity : AppCompatActivity() {
    val binding by lazy { ActivitySomeBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnOpenWeb.setOnClickListener(){
            if (URLUtil.isValidUrl(binding.editUrl.text.toString())){
                val uri: Uri = Uri.parse(binding.editUrl.text.toString())
                val it = Intent(Intent.ACTION_VIEW, uri)
                startActivity(it)
            }
            else{
                Toast.makeText(this,invalid, Toast.LENGTH_LONG).show()
            }

        }
        binding.btnOpenLocaltion.setOnClickListener(){
            val txtLocaltion = binding.edtLocaltion.text.toString()
            val gmmIntentUri = Uri.parse("geo:0,0?q=$txtLocaltion")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage(googleMapPackage)
            startActivity(mapIntent)
        }
        binding.btnShareText.setOnClickListener(){
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, binding.edtShareText.text.toString())
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}
