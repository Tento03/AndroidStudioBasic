package com.example.alarmmanager

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import com.example.alarmmanager.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG_ALARM="TAG"
    }
    lateinit var binding: ActivityMainBinding
    lateinit var calendar: Calendar
    lateinit var picker: MaterialTimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()
        binding.selectTime.setOnClickListener(){
            showAlarmPicker()
        }
        binding.setAlarm.setOnClickListener(){
            setAlarm()
        }
        binding.cancelAlarm.setOnClickListener(){
            cancelAlarm()
        }
    }

    private fun setAlarm() {
        var alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
        val intent=Intent(this,AlarmReceiver::class.java)
        val pendingIntent=PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_MUTABLE)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,pendingIntent
        )
        Toast.makeText(this, "Repeating alarm", Toast.LENGTH_SHORT).show()
    }

    private fun showAlarmPicker() {
        picker=MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()
        picker.show(supportFragmentManager, TAG_ALARM)
        picker.addOnPositiveButtonClickListener(){
            if (picker.hour > 12) {
                binding.selectedTime.text =
                    String.format("%02d", picker.hour - 12) + ":" + String.format(
                        "%02d", picker.minute) + " PM"
            } else if (picker.hour == 12) {
                binding.selectedTime.text =
                    String.format("%02d", picker.hour) + ":" + String.format(
                        "%02d", picker.minute) + " PM"
            } else {
                binding.selectedTime.text =
                    String.format("%02d", picker.hour) + ":" + String.format(
                        "%02d", picker.minute) + " AM"
            }
            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }
    }

    private fun cancelAlarm() {
        var alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
        val intent=Intent(this,AlarmReceiver::class.java)
        val pendingIntent=PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_MUTABLE)
        alarmManager.cancel(pendingIntent)
        Toast.makeText(this,"Alarm Cancelled",Toast.LENGTH_SHORT).show()
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var nama="nama"
            var desc="desc"
            var importance=NotificationManager.IMPORTANCE_HIGH
            val channel=NotificationChannel(AlarmReceiver.CHANNEL_ID,nama,importance).apply {
                description=desc
            }
            val notificationManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}