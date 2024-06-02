package com.example.flowerdelivery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerdelivery.data.Feedback
import com.example.flowerdelivery.data.FeedbackRepository

import kotlinx.coroutines.launch

class FeedbackViewModel(private val repository: FeedbackRepository) : ViewModel() {

    val allFeedback = repository.allFeedback

    fun addFeedback(feedback: Feedback) = viewModelScope.launch {
        repository.addFeedback(feedback)
    }

    fun updateFeedback(feedback: Feedback) = viewModelScope.launch {
        repository.updateFeedback(feedback)
    }

    fun deleteFeedback(feedback: Feedback) = viewModelScope.launch {
        repository.deleteFeedback(feedback)
    }

    fun getFeedbackById(feedbackId: Int) = repository.getFeedbackById(feedbackId)
}
