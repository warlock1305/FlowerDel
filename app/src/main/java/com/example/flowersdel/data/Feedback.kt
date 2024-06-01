package com.example.flowersdel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feedbacks")
data class Feedback(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val rating: Int,
    val description: String,
    val email: String
)