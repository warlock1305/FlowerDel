package com.example.flowerdelivery
import android.content.Context
import com.example.flowerdelivery.data.AppDatabase
import com.example.flowerdelivery.data.Feedback
import com.example.flowerdelivery.data.FeedbackRepository
import com.example.flowerdelivery.data.FlowerRepository
import com.example.flowerdelivery.data.OrderRepository

interface AppContainer {
    val flowerRepository: FlowerRepository
    val feedbackRepository: FeedbackRepository
    val orderRepository: OrderRepository
}

class AppDataContainer(private val context: Context): AppContainer{

    override val flowerRepository: FlowerRepository by lazy{
        FlowerRepository(AppDatabase.getDatabase(context).flowerDao())
    }
    override val feedbackRepository: FeedbackRepository by lazy{
        FeedbackRepository(AppDatabase.getDatabase(context).feedbackDao())
    }

    override val orderRepository: OrderRepository by lazy{
        OrderRepository(AppDatabase.getDatabase(context).orderDao())
    }
}