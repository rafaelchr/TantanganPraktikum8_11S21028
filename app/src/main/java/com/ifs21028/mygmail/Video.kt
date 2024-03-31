package com.ifs21028.mygmail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    var name: String,
    var icon: Int,
    var time: String,
) : Parcelable