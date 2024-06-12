package com.example.flowerdelivery.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Flower::class, Order::class, Feedback::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun flowerDao(): FlowerDao
    abstract fun orderDao(): OrderDao
    abstract fun feedbackDao(): FeedbackDao

    companion object{
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "StudentAPPDatabase")
                    .build().also { Instance = it }
            }
        }

    }
}