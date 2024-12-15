package com.example.flowerdelivery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flowerdelivery.FlowerDeliveryApplication
import com.example.flowerdelivery.viewmodels.FeedbackViewModel
import com.example.flowerdelivery.viewmodels.OrderViewModel

object FlowerViewModelFactory {
    fun factory(application: FlowerDeliveryApplication) = viewModelFactory {
        initializer {
            FeedbackViewModel(application.container.feedbackRepository)
        }
        initializer {
            OrderViewModel(application.container.orderRepository)
        }
        initializer {
            FlowerViewModel(application.container.flowerRepository)
        }
    }
}
