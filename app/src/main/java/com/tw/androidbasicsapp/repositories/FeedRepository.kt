package com.tw.androidbasicsapp.repositories

import android.util.Log
import com.tw.androidbasicsapp.api.Api
import com.tw.androidbasicsapp.models.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedRepository {
    private var newsFeedList: List<News> = ArrayList()
    private var observer: NewsFeedEventListener? = null

    fun getNewsFeeds(): List<News> {
        val news = Api.create().getNewsFeeds()
        news.enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                val body = response.body()
                body?.let{
                    Log.e("Response", "\${response.body()} = " + body)
                    newsFeedList = it
                }

                if (observer != null) {
                    observer!!.onNewsFeedUpdated((newsFeedList as ArrayList<News>))
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                Log.e("Response", "Failed to load data ${t.message}")
            }
        })
        return newsFeedList
    }

    fun setObserver(observer: NewsFeedEventListener) {
        this.observer = observer
    }

    fun getNewsFeed(id: Int): News?{
        val news = Api.create().getNewsFeed(id)
        var newsFeed : News?=null
        news.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                Log.e("Response", "\${response.body()} = " + response.body())
                newsFeed = response.body()!!
                observer?.onNewsFeedUpdated(arrayListOf(newsFeed) as ArrayList<News>)
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.e("Response", "Failed to load data " + t.message)
            }

        })

        return newsFeed
    }

}