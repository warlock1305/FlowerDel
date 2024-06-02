package com.example.flowerdelivery.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feedback")
data class Feedback(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val rating: Int,
    val message: String,
    val email: String
)
