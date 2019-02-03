package com.example.belalkhan.androidviewmodel

import retrofit2.Call
import retrofit2.http.GET

interface Api {


    @GET("marvel")
    fun heroes(): Call<List<Hero>>

}
