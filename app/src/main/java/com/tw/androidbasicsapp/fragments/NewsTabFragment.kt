package com.tw.androidbasicsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayout
import com.tw.androidbasicsapp.R
import com.tw.androidbasicsapp.utilis.Position

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsTabFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news_tab, container, false)
        val tabLayout = view.findViewById<TabLayout>(R.id.newsTabLayout)
        val newsFragment = NewsFragment()
        val bundle = Bundle()
        newsFragment.arguments = bundle
        bundle.putParcelable("position", Position.VERTICAL)
        parentFragmentManager.beginTransaction().replace(R.id.newsTabFragmentContainer, newsFragment)?.commit()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val newsFragment = NewsFragment()
                val bundle = Bundle()

                when {
                    tab?.position == 0 -> {
                        bundle.putParcelable("position", Position.VERTICAL)
                        newsFragment.arguments = bundle
                        parentFragmentManager.beginTransaction().replace(R.id.newsTabFragmentContainer, newsFragment)?.commit()
                    }

                    tab?.position == 1 -> {
                        bundle.putParcelable("position", Position.HORIZONTAL)
                        newsFragment.arguments = bundle
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.newsTabFragmentContainer, newsFragment)?.commit()
                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.e("TAB", "unselected")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.e("TAB", "reselectted")
            }

        })
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsTabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}