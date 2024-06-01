package com.example.flowersdel.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "orders")
@TypeConverters(Converters::class)
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val flowerIds: List<Int>,
    val quantity: Int,
    val orderDate: Long,
    val address: String
)
