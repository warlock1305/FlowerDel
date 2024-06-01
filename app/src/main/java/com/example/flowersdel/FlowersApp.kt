package com.example.flowersdel

import android.app.Application
import android.util.Log
import com.example.flowersdel.data.AppDatabase
import com.example.flowersdel.data.Feedback
import com.example.flowersdel.data.Flower
import com.example.flowersdel.data.Order
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
class FlowersApp : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        val database = AppDatabase.getDatabase(this)
        applicationScope.launch {
            populateDatabase(database)
        }
    }

    private suspend fun populateDatabase(db: AppDatabase) {
        try {
            populateFlowers(db)
            populateOrders(db)
            populateFeedbacks(db)
        } catch (e: Exception) {
            Log.e("DatabaseInitialization", "Error populating database", e)
        }
    }

    private suspend fun populateFlowers(db: AppDatabase) {
        val flowerDao = db.flowerDao()
        flowerDao.insertFlower(Flower(name = "Rose", description = "Red roses are beautiful", price = 10.99, imageUrl = "https://www.aljazeera.com/wp-content/uploads/2021/02/roses.jpg?resize=1800%2C1800"))
        flowerDao.insertFlower(Flower(name = "Orchid", description = "Orchids are amazing", price = 15.99, imageUrl = "https://hips.hearstapps.com/hmg-prod/images/blue-mystique-orchid-plant-royalty-free-image-1706109114.jpg?crop=0.643xw:0.907xh;0.0176xw,0&resize=1200:*"))
    }

    private suspend fun populateOrders(db: AppDatabase) {
        val orderDao = db.orderDao()
        orderDao.insertOrder(Order(flowerIds = listOf(1, 2), quantity = 5, orderDate = System.currentTimeMillis(), address = "IBU"))
    }

    private suspend fun populateFeedbacks(db: AppDatabase) {
        val feedbackDao = db.feedbackDao()
        feedbackDao.insertFeedback(Feedback(rating = 5, description = "Great service", email = "gg@mail.com"))
    }
}
