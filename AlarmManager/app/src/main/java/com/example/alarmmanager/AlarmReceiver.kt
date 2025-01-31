package com.example.alarmmanager

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {
    companion object{
        const val CHANNEL_ID="ID"
        const val NOTIFICATION_ID=1
    }
    override fun onReceive(p0: Context?, p1: Intent?) {
        var intent=Intent(p0,MainActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        var pendingIntent=PendingIntent.getActivity(p0,0,intent,PendingIntent.FLAG_MUTABLE)
        var notificationBuilder= p0?.let {
            NotificationCompat.Builder(it, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Notification")
                .setContentText("Alarm")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }
        if (notificationBuilder != null) {
            with(p0?.let { NotificationManagerCompat.from(it) }){
                this?.notify(NOTIFICATION_ID,notificationBuilder.build())
            }
        }
    }
}