package com.noiid.githubusers.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    var username: String,
    var avatar: String
) : Parcelable
