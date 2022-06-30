package com.tw.androidbasicsapp.util

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Position : Parcelable {
        HORIZONTAL,
        VERTICAL
}