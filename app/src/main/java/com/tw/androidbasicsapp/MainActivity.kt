package com.tw.androidbasicsapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
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
    }
}