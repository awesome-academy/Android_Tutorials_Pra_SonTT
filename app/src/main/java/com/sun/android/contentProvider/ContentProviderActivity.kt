package com.sun.android.contentProvider

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.sun.android.databinding.ActivityContentProviderBinding


class ContentProviderActivity : AppCompatActivity(),ActivityCompat.OnRequestPermissionsResultCallback {
    private val binding by lazy { ActivityContentProviderBinding.inflate(layoutInflater) }
    private var contact: MutableList<Contact> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val permissions = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)

        if (permissions != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, Array(1) { Manifest.permission.READ_CONTACTS }, 111)

        } else {
            readContact()
        }

        val adapter = ContentRecyclerAdapter(contact)
        binding.recyclerview.adapter = adapter
        adapter.setOnClickListener(object : Recycler0nClickListener {
            override fun onItemClick(pos: Int) {
                Toast.makeText(applicationContext, "Click", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val intent = intent
            finish()
            startActivity(intent)
        }
    }

    private fun readContact() {
        val phones = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )
        while (phones?.moveToNext() == true) {
            val name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val ct = Contact(number = phoneNumber, name = name)
            contact.add(ct)
        }
        phones?.close()
    }
}


