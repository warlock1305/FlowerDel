package com.example.flowerdelivery.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerdelivery.data.Order
import com.example.flowerdelivery.data.OrderRepository
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
    fun deleteOrderById(id: Int) = viewModelScope.launch {
        repository.deleteOrderById(id)
    }
}