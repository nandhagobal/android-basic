package com.tw.androidbasicsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.tw.androidbasicsapp.models.News
import com.tw.androidbasicsapp.repositories.FeedRepository
import com.tw.androidbasicsapp.repositories.NewsFeedEventListener
import java.lang.Exception

class NewsFeedActivity : AppCompatActivity(), NewsFeedEventListener {
    private lateinit var displayImageView: ImageView
    private lateinit var titleTextView : TextView
    private lateinit var contentTextView : TextView
    private lateinit var shareButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.e("link",intent.data?.getQueryParameter("id").toString())
        val id = if (intent.data != null) intent.data?.getQueryParameter("id")?.toInt() else intent.extras?.get("id") as Int
        Log.e("id",id.toString())
        setContentView(R.layout.activity_news_feed)
        displayImageView= findViewById(R.id.imageView)
        titleTextView = findViewById(R.id.titleTextView)
        contentTextView = findViewById(R.id.contentTextView)
        shareButton = findViewById(R.id.shareButton)

        shareButton.setOnClickListener{
            val intent = Intent().apply {
                action= Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,"http://www.topnews.com/news?id=$id")
                type = "text/plain"
            }
            this.startActivity(intent)
        }

        val repository = FeedRepository()
        repository.setObserver(this)
        id?.let { repository.getNewsFeed(it) }
    }



    override fun onNewsFeedUpdated(newsFeeds: ArrayList<News>) {
        titleTextView.text=newsFeeds[0].title
        contentTextView.text=newsFeeds[0].content

        class PicassoHandler : Callback {
            override fun onSuccess() {
                Log.e("NewsFeed", "Image loaded")
            }

            override fun onError(e: Exception?) {
                Log.e("NewsFeed", "Image not loaded")
            }

        }

        Picasso
            .get()
            .load(newsFeeds[0].imageURL)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(displayImageView, PicassoHandler())
    }
}
