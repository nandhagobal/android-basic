package com.tw.androidbasicsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentContainerView
import com.tw.androidbasicsapp.fragments.EmailFragment
import com.tw.androidbasicsapp.fragments.MusicPlayerFragment
import com.tw.androidbasicsapp.services.PlayMusicService

class MainActivity : AppCompatActivity() {
    private lateinit var emailButton : Button;
    private lateinit var musicPlayerButton : Button;
    private lateinit var fragmentContainer:FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailButton = findViewById(R.id.emailButton)
        musicPlayerButton = findViewById(R.id.musicPlayerButton)
        fragmentContainer = findViewById(R.id.fragmentContainerView)

        emailButton.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,EmailFragment()).commit()
        }
        musicPlayerButton.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,MusicPlayerFragment()).commit()
        }
    }
}