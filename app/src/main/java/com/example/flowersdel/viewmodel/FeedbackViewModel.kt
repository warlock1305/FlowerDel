package com.example.flowersdel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersdel.data.Feedback
import com.example.flowersdel.data.FeedbackRepository
import kotlinx.coroutines.launch

class FeedbackViewModel(private val repository: FeedbackRepository) : ViewModel() {

    fun insertFeedback(feedback: Feedback) = viewModelScope.launch {
        repository.insertFeedback(feedback)
    }

    fun deleteFeedback(feedback: Feedback) = viewModelScope.launch {
        repository.deleteFeedback(feedback)
    }
}