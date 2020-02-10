package com.example.notificationtest

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel: NotificationChannel
    private lateinit var  builder: NotificationCompat.Builder
    private val channelId = "com.example.notificationtest"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager



        btn_notify.setOnClickListener{

            val intent = Intent(this,LauncherActivity::class.java)


            notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = NotificationCompat.Builder(this,channelId)
                .setContentTitle("Rubber Ducky")
                .setContentText("Test Notification")
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.ic_launcher))
                .setContentIntent(pendingIntent)

            with(NotificationManagerCompat.from(this)){
                notify(1234, builder.build())
            }

        }




    }
}
