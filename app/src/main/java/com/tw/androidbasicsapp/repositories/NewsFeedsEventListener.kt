package com.tw.androidbasicsapp.repositories

import com.tw.androidbasicsapp.models.News

interface NewsFeedsEventListener {
    fun onNewsFeedUpdated(newsFeeds :ArrayList<News>)
}