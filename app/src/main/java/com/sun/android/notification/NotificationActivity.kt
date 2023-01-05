package com.sun.android.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.sun.android.MainActivity
import com.sun.android.R
import com.sun.android.databinding.ActivityNotificationBinding


class NotificationActivity : AppCompatActivity() {
    private val binding by lazy { ActivityNotificationBinding.inflate(layoutInflater) }
    private val mReceiver = NotifyReceiver()

    private var mNotifyManager: NotificationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        registerReceiver(mReceiver, IntentFilter(ACTION_UPDATE_NOTIFICATION));

        setNotificationButtonState(isNotifyEnabled = true, isUpdateEnabled = false, isCancelEnabled = false)
        binding.buttonNotify.setOnClickListener {
            sendNotification()
            setNotificationButtonState(isNotifyEnabled = false, isUpdateEnabled = true, isCancelEnabled = true)
        }
        binding.buttonUpdateNotify.setOnClickListener {
            updateNotification()
            setNotificationButtonState(isNotifyEnabled = true, isUpdateEnabled = false, isCancelEnabled = true)
        }
        binding.buttonCancel.setOnClickListener {
            cancelNotification()
            setNotificationButtonState(isNotifyEnabled = true, isUpdateEnabled = true, isCancelEnabled = false)
        }
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        mNotifyManager = getSystemService(NOTIFICATION_SERVICE) as? NotificationManager
        if (android.os.Build.VERSION.SDK_INT >=
            android.os.Build.VERSION_CODES.O
        ) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                "Mascot Notification", NotificationManager.IMPORTANCE_HIGH
            )
            with(notificationChannel) {
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                description = getString(R.string.notified_description)
                mNotifyManager?.createNotificationChannel(this)
            }

        }
    }

    private fun updateNotification() {
        val androidImage: Bitmap = BitmapFactory
            .decodeResource(resources, R.drawable.donut_circle)
        val notifyBuilder = getNotificationBuilder()
        notifyBuilder.setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle(getString(R.string.notified_update))
        )
        mNotifyManager?.notify(NOTIFICATION_ID, notifyBuilder.build())
    }

    private fun cancelNotification() {
        mNotifyManager?.cancel(NOTIFICATION_ID)
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder {
        val notificationIntent = Intent(this, MainActivity::class.java)

        val notificationPendingIntent = PendingIntent.getActivity(
            this,
            NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )


        val notifyBuilder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            this.setContentTitle(getString(R.string.notified_title))
                .setContentText(getString(R.string.notified_title))
                .setSmallIcon(R.drawable.donut_circle)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true)
        }
        return notifyBuilder
    }

    private fun sendNotification() {
        val intent = Intent(ACTION_UPDATE_NOTIFICATION)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val notifyBuilder = getNotificationBuilder()
        notifyBuilder.addAction(0, getString(R.string.notified_button_update_now), pendingIntent)
        mNotifyManager?.notify(NOTIFICATION_ID, notifyBuilder.build())

    }

    private fun setNotificationButtonState(
        isNotifyEnabled: Boolean?,
        isUpdateEnabled: Boolean?,
        isCancelEnabled: Boolean?
    ) {
        binding.buttonNotify.isEnabled = isNotifyEnabled ?: false
        binding.buttonUpdateNotify.isEnabled = isUpdateEnabled ?: false
        binding.buttonCancel.isEnabled = isCancelEnabled ?: false
    }

    inner class NotifyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            updateNotification()
        }
    }

    companion object {
        private const val CHANNEL_ID = "primary_notification_channel"
        private const val NOTIFICATION_ID = 0
        private const val ACTION_UPDATE_NOTIFICATION = "com.example.android.notify.ACTION_UPDATE_NOTIFICATION"
    }
}
