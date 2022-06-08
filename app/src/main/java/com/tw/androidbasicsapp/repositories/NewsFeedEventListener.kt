package com.tw.androidbasicsapp.repositories

import com.tw.androidbasicsapp.models.News

interface NewsFeedEventListener {
    fun onNewsFeedUpdated(newsFeeds :ArrayList<News>)
}