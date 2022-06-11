package com.tw.androidbasicsapp.adapters


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.tw.androidbasicsapp.activities.NewsFeedActivity
import com.tw.androidbasicsapp.R
import com.tw.androidbasicsapp.models.News

class NewsAdapter(private val context: FragmentActivity?,private var newsFeeds: ArrayList<News>,private val layout:Int) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return newsFeeds.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val newsCard : CardView = view.findViewById(R.id.newsCard)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = newsFeeds[position].title
        holder.newsCard.setOnClickListener {
            val intent = Intent(context, NewsFeedActivity::class.java)
            intent.putExtra("id", newsFeeds[position].id)
            startActivity(context as Context, intent, null)
        }
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


