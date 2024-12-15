package com.example.flowerdelivery

import android.app.Application

class FlowerDeliveryApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}

