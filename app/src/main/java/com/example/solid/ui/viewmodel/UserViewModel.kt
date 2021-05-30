package com.example.solid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.solid.database.entity.UserEntity
import com.example.solid.repository.Resources
import com.example.solid.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    fun save(userEntity: UserEntity): LiveData<Long> {
        return repository.save(userEntity)
    }

    fun list(): LiveData<Resources<List<UserEntity>>> {
        return repository.list()
    }
}