package com.example.flowersdel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersdel.data.Order
import com.example.flowersdel.data.OrderRepository
import kotlinx.coroutines.launch

class OrderViewModel(private val repository: OrderRepository) : ViewModel() {

    val allOrders = repository.allOrders

    fun addOrder(order: Order) = viewModelScope.launch {
        repository.addOrder(order)
    }

    fun updateOrder(order: Order) = viewModelScope.launch {
        repository.updateOrder(order)
    }

    fun deleteOrder(order: Order) = viewModelScope.launch {
        repository.deleteOrder(order)
    }

    fun getOrdersByUser(userId: Int) = repository.getOrdersByUser(userId)
}