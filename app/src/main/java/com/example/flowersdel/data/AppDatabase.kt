package com.example.flowersdel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Flower::class, Order::class, Feedback::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun flowerDao(): FlowerDao
    abstract fun orderDao(): OrderDao
    abstract fun feedbackDao(): FeedbackDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "flowers_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}