package com.tw.androidbasicsapp.utilis

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Position : Parcelable {
        HORIZONTAL,
        VERTICAL
}