package com.tw.androidbasicsapp.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.tw.androidbasicsapp.R
import com.tw.androidbasicsapp.databinding.FragmentComposeUiBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ComposeUIFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComposeUIFragment : Fragment() {
    private lateinit var binding: FragmentComposeUiBinding

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

        binding = FragmentComposeUiBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeText.setContent {
            CardUI()
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun CardUI() {
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 10.dp,
            modifier = Modifier
                .padding(10.dp)
                .height(150.dp),
            onClick = {Log.e("Testing","clicking function of card")}
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = rememberImagePainter("https://pbs.twimg.com/profile_images/1410154790001205250/doYOQtrs_400x400.jpg"),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .width(50.dp)
                        .weight(1f)
                )
                Text(
                    text = "Title of the news headline",
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(4f)
                )
                IconButton(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .weight(1f),
                    onClick = { Log.e("testing", "clicking function of icon button") },
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.menu_vertical),
                            contentDescription = null
                        )
                    }
                )
            }
        }

    }

    private fun IconListenerFun() {
        Log.e("testing on IconButton", "Working")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ComposeUIFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ComposeUIFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}