package com.ifs21028.mygmail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mail(
    var icon: Int,
    var sender: String,
    var title: String,
    var content: String,
) : Parcelable