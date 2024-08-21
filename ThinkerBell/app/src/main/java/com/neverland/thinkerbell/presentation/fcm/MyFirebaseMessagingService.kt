package com.neverland.thinkerbell.presentation.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.presentation.view.home.HomeActivity

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data.isNotEmpty()) {
            val title = remoteMessage.data["title"].toString()
            val body = remoteMessage.data["body"].toString()
            val url = remoteMessage.data["url"].toString()

            sendNotification(title, body, url)
        } else {
            remoteMessage.notification?.let {
                LoggerUtil.d("${it.title}\n${it.body}")
                sendNotification(
                    it.title.toString(),
                    it.body.toString(),
                    null
                )
            }
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sendRegistrationToServer(token)
    }

    private fun scheduleJob() {
        val work = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .build()
        WorkManager.getInstance(this)
            .beginWith(work)
            .enqueue()
    }

    private fun handleNow() {
        LoggerUtil.d("Short lived task is done.")
    }

    private fun sendRegistrationToServer(token: String?) {
        LoggerUtil.d("sendRegistrationTokenToServer($token)")
    }

    private fun sendNotification(title: String, body: String, url: String?) {
        val intent = if (url.isNullOrEmpty()) {
            Intent(this, HomeActivity::class.java)
        } else {
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = "fcm_default_channel"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify((System.currentTimeMillis()).toInt(), notificationBuilder.build())
    }

    internal class MyWorker(appContext: Context, workerParams: WorkerParameters) :
        Worker(appContext, workerParams) {
        override fun doWork(): Result {
            return Result.success()
        }
    }

    fun getRegistrationToken(callback: (String?) -> Unit) {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    callback(token)
                } else {
                    LoggerUtil.w("Fetching FCM Registration Token failed\n${task.exception}")
                    callback(null)
                }
            }
    }
}
