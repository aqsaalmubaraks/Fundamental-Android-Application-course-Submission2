package com.example.mygithub.main.retro

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    private const val URL = "https://api.github.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiReq = retrofit.create(APIEndPoint::class.java)
}
