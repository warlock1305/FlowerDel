package com.example.flowerdelivery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerdelivery.data.Order
import com.example.flowerdelivery.data.OrderRepository
import kotlinx.coroutines.launch

class OrderViewModel(private val repository: OrderRepository) : ViewModel() {

    val allOrders = repository.allOrders
    fun insertOrder(order: Order) {
        viewModelScope.launch {
            repository.insertOrder(order)
        }
    }
    fun getOrderById(orderId: Int) = repository.getOrderById(orderId)
}