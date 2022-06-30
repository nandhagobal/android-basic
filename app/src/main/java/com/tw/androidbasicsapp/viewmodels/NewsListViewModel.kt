package com.tw.androidbasicsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tw.androidbasicsapp.models.News
import com.tw.androidbasicsapp.repositories.FeedRepository
import com.tw.androidbasicsapp.repositories.NewsFeedsEventListener
import com.tw.androidbasicsapp.util.Resource

class NewsListViewModel(private val newsFeedRepository: FeedRepository) : ViewModel(), NewsFeedsEventListener {

    val newsFeedList = MutableLiveData<Resource<ArrayList<News>>>()

    init {
        newsFeedRepository.setObserver(this)
        fetchNewsFeed();
    }
    fun getNewsFeedList():LiveData<Resource<ArrayList<News>>>{
        newsFeedList.postValue(Resource.loading(null))
        return newsFeedList;
    }
    private fun fetchNewsFeed () {
        newsFeedRepository.getNewsFeeds()
    }

    override fun onNewsFeedUpdated(newsFeeds: ArrayList<News>) {
        newsFeedList.postValue(Resource.success(newsFeeds))
    }
}