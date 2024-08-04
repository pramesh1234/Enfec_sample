package com.app.sample101.data.service

import com.app.sample101.data.model.UserModel
import com.app.sample101.data.utils.Resource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    suspend fun getUsers() : Response<List<UserModel>>
}