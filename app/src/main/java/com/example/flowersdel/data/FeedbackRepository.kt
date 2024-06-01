package com.example.flowersdel.data

class FeedbackRepository(private val feedbackDao: FeedbackDao) {
    suspend fun insertFeedback(feedback: Feedback) {
        feedbackDao.insertFeedback(feedback)
    }

    suspend fun deleteFeedback(feedback: Feedback) {
        feedbackDao.deleteFeedback(feedback)
    }
}
