package com.example.belalkhan.androidviewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeroesViewModel : ViewModel() {

    //this is the data that we will fetch asynchronously
    private var heroList: MutableLiveData<List<Hero>>? = null

    //we will call this method to get the data
    //if the list is null
    //we will load it asynchronously from server in this method
    //finally we will return the list
    val heroes: LiveData<List<Hero>>
        get() {
            if (heroList == null) {
                heroList = MutableLiveData()
                loadHeroes()
            }
            return heroList!!
        }


    //This method is using Retrofit to get the JSON data from URL
    private fun loadHeroes() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://simplifiedcoding.net/demos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(Api::class.java)
        val call = api.heroes()


        call.enqueue(object : Callback<List<Hero>> {
            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {

                //finally we are setting the list to our MutableLiveData
                heroList!!.value = response.body()
            }

            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {

            }
        })
    }
}
