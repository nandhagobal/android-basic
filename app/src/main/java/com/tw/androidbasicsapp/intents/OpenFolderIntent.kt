package com.example.movieapp.intents

import android.content.Intent

class OpenFolderIntent {
    open fun pickImage() :Intent {
        var intent = Intent()
        intent.type="image/*"
        intent.action= Intent.ACTION_GET_CONTENT
        return intent
    }

    open fun pickSong():Intent{
        var intent = Intent()
        intent.type="audio/*"
        intent.action= Intent.ACTION_GET_CONTENT
        return intent
    }

}