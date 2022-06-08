package com.example.movieapp.intents

import android.content.Intent
import android.net.Uri

class SendIntent {
    public fun sendMailWithAttachment(
        toAddress: String,
        subject: String,
        body: String,
        attachmentUri: Uri?
    ): Intent {
        var intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, body)
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, subject)
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(toAddress))
            type = "text/plain"
            putExtra(Intent.EXTRA_STREAM, attachmentUri)
            type = "image/*"

        }
        return intent;
    }
}