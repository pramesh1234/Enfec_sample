package com.app.sample101.data.repo

import com.app.sample101.data.RetrofitInstance
import com.app.sample101.data.model.UserModel
import com.app.sample101.data.utils.ApiResponse
import com.app.sample101.data.utils.NetworkRequest
import com.app.sample101.data.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository {
     suspend fun getUserData(): Flow<Resource<List<UserModel>>> = flow{
        emit(Resource.Loading())
        try {
            NetworkRequest.process {
                RetrofitInstance.userService.getUsers()
            }.run {
                when (this) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(data))
                    }

                    is ApiResponse.Failure -> {
                        emit(Resource.Error(message))
                    }
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error())
        }
    }
}