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

/**
 * A simple [Fragment] subclass.
 * Use the [EmailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EmailFragment : Fragment() {
    private lateinit var activityButton : Button
    private lateinit var attachmentButton : Button
    private lateinit var imageDisplay : ImageView
    private lateinit var toAddressET: EditText
    private lateinit var bodyET: EditText
    private lateinit var subjectET: EditText
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private var imageUri: Uri? =null
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

        activityButton= view.findViewById(R.id.activityButton)
        attachmentButton= view.findViewById(R.id.attachmentButton)
        imageDisplay= view.findViewById(R.id.imageDisplay)
        toAddressET = view.findViewById(R.id.toAddressET)
        bodyET = view.findViewById(R.id.bodyET)
        subjectET = view.findViewById(R.id.subjectET)

        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    imageUri = result.data?.data
                    imageDisplay.setImageURI(imageUri)
                    imageDisplay.visibility=ImageView.VISIBLE
                }
            }
        activityButton.setOnClickListener{
            val sendIntent = SendIntent()
            val intent =sendIntent.sendMailWithAttachment(toAddressET.text.toString(), subjectET.text.toString(), bodyET.text.toString(),imageUri)
            startActivity(intent)

        attachmentButton.setOnClickListener{
            Log.e("attach","working")
            val openFolderIntent = OpenFolderIntent()
            val pickImageIntent = openFolderIntent.pickImage()
            startForResult.launch(pickImageIntent)
        }
    }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EmailFragment.
         */
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