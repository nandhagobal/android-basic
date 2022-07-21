package com.tw.androidbasicsapp.activities

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.tw.androidbasicsapp.recievers.AppBroadcastReceiver
import com.tw.androidbasicsapp.R
import com.tw.androidbasicsapp.di.newsModule
import com.tw.androidbasicsapp.fragments.ComposeUIFragment
import com.tw.androidbasicsapp.fragments.EmailFragment
import com.tw.androidbasicsapp.fragments.MusicPlayerFragment
import com.tw.androidbasicsapp.fragments.NewsTabFragment
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentContainer:FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Toast.makeText(this,"hi",Toast.LENGTH_LONG)
        startKoin{
            androidLogger()
            androidContext(this@MainActivity)
            modules(newsModule)
        }

        fragmentContainer = findViewById(R.id.fragmentContainerView)

        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.musicItem

        val bottomNavigation = findViewById<NavigationBarView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener {
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
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,NewsTabFragment()).commit()
                    true
                }
                R.id.UIItem -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,ComposeUIFragment()).commit()
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