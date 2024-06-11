package com.example.flowerdelivery.data

import com.example.flowerdelivery.data.Order
import com.example.flowerdelivery.data.OrderDao
import kotlinx.coroutines.flow.Flow

class OrderRepository(private val orderDao: OrderDao) {

    val allOrders: Flow<List<Order>> = orderDao.getAllOrders()

    suspend fun insertOrder(order: Order) {
        orderDao.insertOrder(order)
    }

    fun getOrderById(orderId: Int): Flow<Order?> {
        return orderDao.getOrderById(orderId)
    }
}