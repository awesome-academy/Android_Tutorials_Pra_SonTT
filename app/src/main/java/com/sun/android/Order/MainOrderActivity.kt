package com.sun.android.Order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.sun.android.EXTRA_REV
import com.sun.android.R
import com.sun.android.SecondActivity

class MainOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_order)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu?.let { it.findItem(R.id.night_mode).setTitle(R.string.day_mode) }
        } else {
            menu?.let { it.findItem(R.id.night_mode).setTitle(R.string.night_mode) }
        }
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.night_mode -> if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            R.id.action_order -> {
                val intent = Intent(this, OrderActivity::class.java)
                intent.putExtra(EXTRA_REV, "d")
                startActivity(intent)
            }
            R.id.action_settings -> {
                val intent = Intent(this, useDialogActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}
