package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()
        binding.btnSend1.setOnClickListener(){
            var intent=Intent(this,MainActivity::class.java).apply {
                flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
            var pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)

            var notificationBuilder=NotificationCompat.Builder(this,NotificationReceiver.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_favorite_24)
                .setContentTitle(binding.etTitle.text)
                .setContentText(binding.etMessage.text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)){
                notify(NotificationReceiver.NOTIFICATION_ID,notificationBuilder.build())
            }
        }

        binding.btnSend2.setOnClickListener(){
            var intent=Intent(this,MainActivity::class.java).apply {
                flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            var pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)

            var notificationBuilder=NotificationCompat.Builder(this,NotificationReceiver.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_favorite_24)
                .setContentTitle(binding.etTitle.text)
                .setContentText(binding.etMessage.text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            with(NotificationManagerCompat.from(this)){
                notify(NotificationReceiver.NOTIFICATION_ID,notificationBuilder.build())
            }
        }
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var nama="nama"
            var desc="desc"
            var importance=NotificationManager.IMPORTANCE_HIGH
            var channel1=NotificationChannel(NotificationReceiver.CHANNEL_ID,nama,importance).apply {
                description=desc
            }
            var channel2=NotificationChannel(NotificationReceiver.CHANNEL_ID,nama,importance).apply {
                description=desc
            }
            var notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel1)
            notificationManager.createNotificationChannel(channel2)
        }
    }
}