package com.example.mygithub.main.retro

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface APIEndPoint {
    @GET("search/users")
    @Headers("Authorization: token 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")

    fun getUserSearch(@Query("q") query: String): Call<PeopleArray>
    @GET("users/{username}")
    @Headers("Authorization: token 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")

    fun getUserDetail(
        @Path("username") username: String
    ): Call<PeopleDetail>
    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")

    fun getUserFollowers(
        @Path("username") username: String
    ): Call<ArrayList<PeopleModel>>
    @GET("users/{username}/following")
    @Headers("Authorization: token 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")

    fun getUserFollowing(
        @Path("username") username: String
    ): Call<ArrayList<PeopleModel>>
}
