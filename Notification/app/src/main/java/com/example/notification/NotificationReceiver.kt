package com.example.notification

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class NotificationReceiver:BroadcastReceiver() {
    companion object{
        const val NOTIFICATION_ID=1
        const val CHANNEL_ID="id"
    }
    override fun onReceive(p0: Context?, p1: Intent?) {
    }
}