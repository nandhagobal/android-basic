package com.tw.androidbasicsapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AppBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            if (intent.extras?.getBoolean("state") == true)
                Toast.makeText(context, "No Internet", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context, "connection is back", Toast.LENGTH_LONG).show()
        }
        if (intent.action == Intent.ACTION_POWER_CONNECTED) {
            Toast.makeText(context, "charging", Toast.LENGTH_SHORT).show()
        }
        if (intent.action == Intent.ACTION_POWER_DISCONNECTED) {
            Toast.makeText(context, "unplugged", Toast.LENGTH_SHORT).show()
        }

    }
}
