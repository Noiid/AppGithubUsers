package com.noiid.githubusers

import com.noiid.githubusers.resource.GithubResponse
import com.noiid.githubusers.resource.Users
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users?q={id}")
    fun getListSearch(
        @Path("id") id: String
    ): Call<GithubResponse>

    @GET("users/{id}")
    fun getUser(
        @Path("id") id: String
    ): Call<Users>


}