package com.example.flowerdelivery.data

import kotlinx.coroutines.flow.Flow

class FeedbackRepository(private val feedbackDao: FeedbackDao) {
    val allFeedback: Flow<List<Feedback>> = feedbackDao.getAllFeedback()

    suspend fun addFeedback(feedback: Feedback) = feedbackDao.insertFeedback(feedback)


    suspend fun updateFeedback(feedback: Feedback) = feedbackDao.updateFeedback(feedback)


    suspend fun deleteFeedback(feedback: Feedback) = feedbackDao.deleteFeedback(feedback)


    fun getFeedbackById(feedbackId: Int): Flow<Feedback?> = feedbackDao.getFeedbackById(feedbackId)

}