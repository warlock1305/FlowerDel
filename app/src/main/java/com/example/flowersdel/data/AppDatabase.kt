package com.example.flowersdel.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Flower::class, Order::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun flowerDao(): FlowerDao
    abstract fun orderDao(): OrderDao
    abstract fun userDao(): UserDao
}