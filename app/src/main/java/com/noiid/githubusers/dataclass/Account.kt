package com.noiid.githubusers.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(
    var username: String,
    var avatar: Int,
    var name: String,
    var location: String,
    var company: String,
    var repository: Int,
    var follower: Int,
    var following: Int
) : Parcelable
