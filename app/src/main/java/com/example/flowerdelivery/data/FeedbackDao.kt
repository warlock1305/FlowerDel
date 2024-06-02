package com.example.flowerdelivery.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedbackDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFeedback(feedback: Feedback)

    @Update
    suspend fun updateFeedback(feedback: Feedback)

    @Delete
    suspend fun deleteFeedback(feedback: Feedback)

    @Query("SELECT * FROM feedback")
    fun getAllFeedback(): Flow<List<Feedback>>

    @Query("SELECT * FROM feedback WHERE id = :feedbackId")
    fun getFeedbackById(feedbackId: Int): Flow<Feedback>

}
