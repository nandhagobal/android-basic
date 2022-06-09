package com.tw.androidbasicsapp.activities

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tw.androidbasicsapp.AppBroadcastReceiver
import com.tw.androidbasicsapp.R
import com.tw.androidbasicsapp.fragments.EmailFragment
import com.tw.androidbasicsapp.fragments.MusicPlayerFragment
import com.tw.androidbasicsapp.fragments.NewsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentContainer:FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.fragmentContainerView)

        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.musicItem

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener{
            Log.e("listerner check","entered")
            when(it.itemId) {
                R.id.emailItem -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,EmailFragment()).commit()
                    true
                }
                R.id.musicItem -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,MusicPlayerFragment()).commit()
                    true
                }
                R.id.newsItem -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,NewsFragment()).commit()
                    true
                }
                else -> false
            }
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