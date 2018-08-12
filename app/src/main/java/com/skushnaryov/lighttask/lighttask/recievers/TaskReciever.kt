package com.skushnaryov.lighttask.lighttask.recievers

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.skushnaryov.lighttask.lighttask.Constants
import com.skushnaryov.lighttask.lighttask.R
import com.skushnaryov.lighttask.lighttask.activities.MainActivity

class TaskReciever : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val name = intent.getStringExtra(Constants.EXTRAS_NAME)
        val id = intent.getIntExtra(Constants.EXTRAS_ID, -1)

        if (name != null && !name.isEmpty() && id != -1) {
            createNotification(context, name, id)
        }
    }

    private fun createNotification(context: Context ,taskName: String, id: Int) {
        val clickIntent = Intent(context, MainActivity::class.java)
        val clickPending = PendingIntent.getActivity(context, 0, clickIntent, 0)

        val doneIntent = Intent(context, TaskDoneReciever::class.java)
        doneIntent.action = Constants.TASK_DONE_RECIEVER
        doneIntent.putExtra(Constants.EXTRAS_ID, id)

        val donePending = PendingIntent.getBroadcast(context, 0, doneIntent, 0)

        val builder = NotificationCompat.Builder(context, Constants.TASKS_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getString(R.string.taskRecieverTitle))
                .setContentText(taskName)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_done_black_24dp,
                        context.getString(R.string.doneRecieverAction), donePending)
                .setContentIntent(clickPending)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.priority = NotificationCompat.PRIORITY_HIGH
            builder.setDefaults(NotificationCompat.DEFAULT_ALL)
        }

        val notification = builder.build()

        NotificationManagerCompat.from(context).notify(id, notification)
    }
}