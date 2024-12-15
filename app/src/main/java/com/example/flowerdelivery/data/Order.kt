package com.example.flowerdelivery.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val orderDate: String,
    val address: String,
    @TypeConverters(Converters::class) val productIds: List<Int>,
    @TypeConverters(Converters::class) val productQuantities: List<Int>
)