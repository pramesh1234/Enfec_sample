package com.app.sample101.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.sample101.data.model.UserModel
import com.app.sample101.data.repo.UserRepository
import com.app.sample101.data.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

class UserViewModel : ViewModel() {
    var userRepository: UserRepository = UserRepository()

    private val _userList = MutableStateFlow<List<UserModel>>(emptyList())
    var userList = _userList.asStateFlow()
    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading = _isLoading

    suspend fun getUserData() {
        return userRepository.getUserData().collectLatest {
            when(it){
               is  Resource.Success ->{
                   _isLoading.postValue(false)
                    _userList.emit(it.data?:return@collectLatest)
                }
                is Resource.Loading->{
                    _isLoading.postValue(true)
                }
                else->{

                }
            }
        }
    }
}