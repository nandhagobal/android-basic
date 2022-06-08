package com.tw.androidbasicsapp.fragments

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.tw.androidbasicsapp.R
import com.tw.androidbasicsapp.services.PlayMusicService

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MusicPlayerFragment : Fragment() {
    private lateinit var playMusicButton: Button
    private lateinit var chooseButton: Button
    private lateinit var endTimeTV: TextView
    private var intentService: Intent? = null
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private var uri: String? = null
    private var isPlaying = false

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
        val view = inflater.inflate(R.layout.fragment_music_player, container, false)
        endTimeTV = view.findViewById(R.id.endTimeTV)
        playMusicButton = view.findViewById(R.id.playMusicButton)
        chooseButton = view.findViewById(R.id.chooseButton)

        chooseButton.setOnClickListener {
            val getMusicIntent = Intent().apply {
                action = Intent.ACTION_GET_CONTENT
                type = "audio/*"
            }
            startForResult.launch(getMusicIntent)
        }

        playMusicButton.setOnClickListener {
            if (uri == null)
                Toast.makeText(activity, "Song is not chosen", Toast.LENGTH_LONG).show()
            else {
                intentService = createIntentForService(uri)
                activity?.startService(intentService)
                isPlaying = !isPlaying
                if (isPlaying) playMusicButton.text = "PAUSE"
                else playMusicButton.text = "PLAY"
//                getDurationOfSong()
            }
        }


        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                uri = result.data?.data.toString()
                getDurationOfSong()

            }
        }
        return view
    }

    override fun onDestroyView() {
        if (intentService !== null)
            activity?.stopService(intentService)
        super.onDestroyView()

    }

    private fun getDurationOfSong() {
        val mediaPlayer = MediaPlayer()
        mediaPlayer.reset()
        activity?.let { it1 -> mediaPlayer.setDataSource(it1.applicationContext, Uri.parse(uri)) }
        mediaPlayer.prepare()
        mediaPlayer.setOnPreparedListener {
            val min: Int = it.duration / 60000
            val sec: Int = ((it.duration / 60000) - min) * 60
            endTimeTV.text = "0$min:$sec"
            Log.e("Timing", "0$min:$sec")
        }
    }

    private fun createIntentForService(uri: String?): Intent {
        val intentService = Intent(activity, PlayMusicService::class.java)
        intentService.putExtra("URI", uri)
        return intentService
    }


    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MusicPlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}