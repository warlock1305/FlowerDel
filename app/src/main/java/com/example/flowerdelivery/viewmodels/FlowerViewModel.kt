package com.example.flowerdelivery.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.flowerdelivery.data.Flower
import com.example.flowerdelivery.data.FlowerRepository

class FlowerViewModel(private val repository: FlowerRepository) : ViewModel() {

    val allFlowers = repository.allFlowers

    fun addFlower(flower: Flower) = viewModelScope.launch {
        repository.addFlower(flower)
    }

    fun updateFlower(flower: Flower) = viewModelScope.launch {
        repository.updateFlower(flower)
    }

    fun deleteFlower(flower: Flower) = viewModelScope.launch {
        repository.deleteFlower(flower)
    }

    fun getFlowerById(flowerId: Int): LiveData<Flower?> = repository.getFlowerById(flowerId).asLiveData()
}