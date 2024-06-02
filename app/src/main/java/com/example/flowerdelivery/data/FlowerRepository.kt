package com.example.flowerdelivery.data

import kotlinx.coroutines.flow.Flow

class FlowerRepository(private val flowerDao: FlowerDao) {
    val allFlowers: Flow<List<Flower>> = flowerDao.getAllFlowers()

    suspend fun addFlower(flower: Flower) = flowerDao.insertFlower(flower)


    suspend fun updateFlower(flower: Flower) = flowerDao.updateFlower(flower)


    suspend fun deleteFlower(flower: Flower) = flowerDao.deleteFlower(flower)


    fun getFlowerById(flowerId: Int): Flow<Flower?> = flowerDao.getFlowerById(flowerId)

}