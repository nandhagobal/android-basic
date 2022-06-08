package com.tw.androidbasicsapp.adapters


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.tw.androidbasicsapp.R
import com.tw.androidbasicsapp.models.News

class NewsAdapter(private val context: FragmentActivity?,private var newsFeeds: ArrayList<News>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.activity_feed, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return newsFeeds.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
//        val contentTextView = view.findViewById<TextView>(R.id.contentTextView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = newsFeeds[position].title
//        holder.contentTextView.text = newsFeeds[position].content
//        holder.titleTextView.setOnClickListener {
//            var intent = Intent(context, SingleNewsFeedActivity::class.java)
//            intent.putExtra("id", newsFeeds[position].id)
//            startActivity(context, intent, null)
//        }
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
            .load(newsFeeds[position].imageURL)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imageView, PicassoHandler())
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedNewsFeeds(newsFeeds: List<News>) {
        this.newsFeeds = newsFeeds as ArrayList<News>
        notifyDataSetChanged()

    }

}


