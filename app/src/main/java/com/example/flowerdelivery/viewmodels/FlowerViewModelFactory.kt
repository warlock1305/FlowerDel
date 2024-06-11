package com.example.flowersdel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flowerdelivery.FlowerDeliveryApplication
import com.example.flowerdelivery.data.FlowerRepository
import com.example.flowerdelivery.viewmodels.FeedbackViewModel
import com.example.flowerdelivery.viewmodels.OrderViewModel


object FlowerViewModelFactory {
    val Factory = viewModelFactory {
        initializer {
            FlowerViewModel(
                FlowerDeliveryApplication().container.flowerRepository
            )
        }
        initializer {
            FeedbackViewModel(
                FlowerDeliveryApplication().container.feedbackRepository
            )
        }
        initializer {
            OrderViewModel(
                FlowerDeliveryApplication().container.orderRepository
            )
        }

    }
}

fun CreationExtras.flowerDeliveryApplication(): FlowerDeliveryApplication = (
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                as FlowerDeliveryApplication)