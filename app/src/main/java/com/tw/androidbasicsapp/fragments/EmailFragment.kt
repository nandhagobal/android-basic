package com.tw.androidbasicsapp.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.movieapp.intents.OpenFolderIntent
import com.example.movieapp.intents.SendIntent
import com.tw.androidbasicsapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EmailFragment : Fragment() {
    private lateinit var activityButton: Button
    private lateinit var attachmentButton: Button
    private lateinit var imageDisplay: ImageView
    private lateinit var toAddressEditableText: EditText
    private lateinit var bodyEditableText: EditText
    private lateinit var subjectEditableText: EditText
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private var imageUri: Uri? = null

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
        val view = inflater.inflate(R.layout.fragment_email, container, false)

        activityButton = view.findViewById(R.id.activityButton)
        attachmentButton = view.findViewById(R.id.attachmentButton)
        imageDisplay = view.findViewById(R.id.imageDisplay)
        toAddressEditableText = view.findViewById(R.id.toAddressET)
        bodyEditableText = view.findViewById(R.id.bodyET)
        subjectEditableText = view.findViewById(R.id.subjectET)

        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    imageUri = result.data?.data
                    imageDisplay.setImageURI(imageUri)
                    imageDisplay.visibility = ImageView.VISIBLE
                }
            }
        activityButton.setOnClickListener {
            val sendIntent = SendIntent()
            val intent = sendIntent.sendMailWithAttachment(
                toAddressEditableText.text.toString(),
                subjectEditableText.text.toString(),
                bodyEditableText.text.toString(),
                imageUri
            )
            startActivity(intent)

            attachmentButton.setOnClickListener {
                Log.e("attach", "working")
                val pickImageIntent = OpenFolderIntent().pickImage()
                startForResult.launch(pickImageIntent)
            }
        }

        return view
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EmailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}