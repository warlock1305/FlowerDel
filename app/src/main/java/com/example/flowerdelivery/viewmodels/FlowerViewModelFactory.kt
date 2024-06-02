package com.example.flowersdel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flowerdelivery.data.FlowerRepository

class FlowerViewModelFactory(private val repository: FlowerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlowerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlowerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}