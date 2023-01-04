package com.sun.android.PowerReceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.sun.android.BuildConfig
import com.sun.android.databinding.ActivityReceiverBinding


class ReceiverActivity : AppCompatActivity() {
    val binding by lazy { ActivityReceiverBinding.inflate(layoutInflater) }


    private val mReceiver = CustomReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        this.registerReceiver(mReceiver, filter)


        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, IntentFilter(ACTION_CUSTOM_BROADCAST))

        binding.buttonSendBroadCast.setOnClickListener {
            val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
            LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)
        }
    }

    override fun onDestroy() {
        this.unregisterReceiver(mReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy()
    }

    companion object {
        val ACTION_CUSTOM_BROADCAST: String = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"
    }
}
