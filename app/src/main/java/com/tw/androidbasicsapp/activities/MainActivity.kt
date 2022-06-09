package com.tw.androidbasicsapp.activities

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.tw.androidbasicsapp.AppBroadcastReceiver
import com.tw.androidbasicsapp.R
import com.tw.androidbasicsapp.fragments.EmailFragment
import com.tw.androidbasicsapp.fragments.MusicPlayerFragment
import com.tw.androidbasicsapp.fragments.NewsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var emailButton : Button
    private lateinit var musicPlayerButton : Button
    private lateinit var newsButton : Button
    private lateinit var fragmentContainer:FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailButton = findViewById(R.id.emailButton)
        musicPlayerButton = findViewById(R.id.musicPlayerButton)
        newsButton = findViewById(R.id.newsButton)
        fragmentContainer = findViewById(R.id.fragmentContainerView)

        emailButton.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,EmailFragment()).commit()
        }
        musicPlayerButton.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,MusicPlayerFragment()).commit()
        }

        newsButton.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,NewsFragment()).commit()
        }

        val br: BroadcastReceiver = AppBroadcastReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(br, filter)
    }
}