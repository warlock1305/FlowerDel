package com.example.flowersdel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersdel.data.User
import com.example.flowersdel.data.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val allUsers = repository.allUsers

    fun addUser(user: User) = viewModelScope.launch {
        repository.addUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        repository.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        repository.deleteUser(user)
    }

    fun getUserById(userId: Int) = repository.getUserById(userId)
}
