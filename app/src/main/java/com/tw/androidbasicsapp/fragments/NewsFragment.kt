package com.tw.androidbasicsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tw.androidbasicsapp.R
import com.tw.androidbasicsapp.adapters.NewsAdapter
import com.tw.androidbasicsapp.models.News
import com.tw.androidbasicsapp.repositories.FeedRepository
import com.tw.androidbasicsapp.util.Position
import com.tw.androidbasicsapp.util.Status
import com.tw.androidbasicsapp.viewmodels.NewsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerViewAdapter: NewsAdapter

    private val newsViewModel: NewsListViewModel by viewModel();
//    private lateinit var newsListRepository: FeedRepository;

    // TODO: Rename and change types of parameters
    private var position: Position? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.get("position") as Position?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_news, container, false)

        layoutManager = LinearLayoutManager(activity)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
//        setupViewModel()
        setupObserver()
        position?.let {
            if (position == Position.HORIZONTAL) {
                NewsAdapter(this.activity, arrayListOf(),R.layout.activity_feed_horizontal).also { recyclerViewAdapter = it }
                recyclerView.adapter = recyclerViewAdapter
                recyclerView.layoutManager = LinearLayoutManager(
                    this.activity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            }
            else{
                NewsAdapter(this.activity, arrayListOf(),R.layout.activity_feed_vertical).also { recyclerViewAdapter = it }
                recyclerView.adapter = recyclerViewAdapter
            }
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//    private fun setupViewModel() {
//        newsListRepository = FeedRepository();
//        newsViewModel = ViewModelProvider(this, ViewModelFactory(newsListRepository)). get(NewsListViewModel::class.java)
//    }

    private fun setupObserver() {
        newsViewModel.getNewsFeedList().observe(this.viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                    Log.i("news", "Data loaded!");
                    it.data?.let { newsFeeds -> updateNewsList(newsFeeds) }
                }
        })
    }

    private class ViewModelFactory(val newsListRepository: FeedRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewsListViewModel(newsListRepository) as T
        }
    }

    private fun updateNewsList(newsFeeds: List<News>) {
        recyclerViewAdapter.setUpdatedNewsFeeds(newsFeeds)
    }
}