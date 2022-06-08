package com.tw.androidbasicsapp.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.util.Log

class PlayMusicService : Service() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when {
            mediaPlayer == null -> {
                mediaPlayer = MediaPlayer.create(this, Uri.parse(intent?.extras?.get("URI") as String))
                mediaPlayer?.start()
    //            mediaPlayer?.seekTo(intent?.extras?.get("position") as Int)
            }
            mediaPlayer?.isPlaying == true -> mediaPlayer?.pause()
            else -> {
                mediaPlayer?.start()
            }
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("duration",mediaPlayer?.duration.toString())
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}