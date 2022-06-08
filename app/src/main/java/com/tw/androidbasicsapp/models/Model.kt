package com.tw.androidbasicsapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(var id:Int,var title:String,var content:String,var imageURL:String):Parcelable
