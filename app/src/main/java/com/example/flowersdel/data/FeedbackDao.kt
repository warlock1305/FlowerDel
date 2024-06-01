package com.example.flowersdel.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
@Dao
interface FeedbackDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFeedback(feedback: Feedback)

    @Delete
    suspend fun deleteFeedback(feedback: Feedback)
}