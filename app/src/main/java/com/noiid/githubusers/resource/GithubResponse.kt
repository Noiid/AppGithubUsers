package com.noiid.githubusers.resource

import com.google.gson.annotations.SerializedName

data class GithubResponse (
    @field:SerializedName("total_count")
    val total_count: Int,

    @field:SerializedName("incomplete_results")
    val result: Boolean,

    @field:SerializedName("items")
    val items: List<ListUser>
        )

data class Users (
    @field:SerializedName("login")
    val username: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("company")
    val company: String,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("avatar_url")
    val avatar_url: String,

    @field:SerializedName("followers_url")
    val followers_url: String,

    @field:SerializedName("following_url")
    val following_url: String,
    )

data class ListUser (
    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatar_url: String
        )
