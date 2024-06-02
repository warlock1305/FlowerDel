package com.example.flowerdelivery.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FlowerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFlower(flower: Flower)

    @Update
    suspend fun updateFlower(flower: Flower)

    @Delete
    suspend fun deleteFlower(flower: Flower)

    @Query("SELECT * FROM flowers")
    fun getAllFlowers(): Flow<List<Flower>>

    @Query("SELECT * FROM flowers WHERE id = :flowerId")
    fun getFlowerById(flowerId: Int): Flow<Flower>
}