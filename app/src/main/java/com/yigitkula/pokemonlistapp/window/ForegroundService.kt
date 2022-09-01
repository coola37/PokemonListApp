package com.yigitkula.pokemonlistapp.window

import android.app.Notification

import android.content.Intent
import android.os.IBinder
import android.os.Build
import androidx.annotation.RequiresApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.yigitkula.pokemonlistapp.R
import com.yigitkula.pokemonlistapp.model.Pokemon
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.popup_window.view.*
import java.lang.UnsupportedOperationException

class ForegroundService : Service() {
    private var window: Window? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // create the custom or default notification
        // based on the android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startMyOwnForeground()
        }
        else {
            startForeground(
                1,
                Notification()
            )
        }

        val pokemon: Pokemon = intent?.getParcelableExtra("pokemonData")!!
        window?.close()
        window = Window(this, pokemon)
        window?.open()
        return super.onStartCommand(intent, flags, startId)
    }

    // for android version >=O we need to create
    // custom notification stating
    // foreground service is running
    @RequiresApi(Build.VERSION_CODES.O)
    private fun startMyOwnForeground() {
        val NOTIFICATION_CHANNEL_ID = "example.permanence"
        val channelName = "Background Service"
        val chan = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_MIN
        )
        val manager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?)!!
        manager.createNotificationChannel(chan)
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        val notification: Notification = notificationBuilder.setOngoing(true)
            .setContentTitle("Service running")
            .setContentText("Displaying over other apps") // this is important, otherwise the notification will show the way
            // you want i.e. it will show some default notification
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationManager.IMPORTANCE_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(2, notification)
    }
}