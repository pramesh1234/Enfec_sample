package com.app.sample101.data

import com.app.sample101.data.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private fun getRetrofitInstance():Retrofit{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
       return retrofit
    }
    val userService: UserService = getRetrofitInstance().create(UserService::class.java)

}