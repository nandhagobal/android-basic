package com.example.movieapp.intents

import android.content.Intent

class OpenFolderIntent {
    public fun pickImage() :Intent {
        var intent = Intent()
        intent.type="image/*"
        intent.action= Intent.ACTION_GET_CONTENT
        return intent
    }
}