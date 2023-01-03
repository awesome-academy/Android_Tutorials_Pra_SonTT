package com.sun.android.PowerReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.sun.android.PowerReceiver.ReceiverActivity.Companion.ACTION_CUSTOM_BROADCAST
import com.sun.android.R


class CustomReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val intentAction:String = intent.action.toString()
        if (intentAction.isNotEmpty()) {
            var toastMessage = context.getString(R.string.unknown_intent_action)
            when (intentAction) {
                Intent.ACTION_POWER_CONNECTED -> toastMessage = context.getString(R.string.Power_connected)
                Intent.ACTION_POWER_DISCONNECTED -> toastMessage = context.getString(R.string.Power_disconnected)
                ACTION_CUSTOM_BROADCAST ->
                    toastMessage = context.getString(R.string.Custom_Broadcast_Received)
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }

    }

}
