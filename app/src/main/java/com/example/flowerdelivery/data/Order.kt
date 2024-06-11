package com.example.flowerdelivery.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val flowerId: Int,
    val quantity: Int,
    val orderDate: Long
)