package com.example.flowersdel.data

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val allUsers = userDao.getAllUsers()

    suspend fun addUser(user: User) = userDao.insertUser(user)


    suspend fun updateUser(user: User) = userDao.updateUser(user)


    suspend fun deleteUser(user: User) = userDao.deleteUser(user)


    fun getUserById(userId: Int): Flow<User?> = userDao.getUserById(userId)

}