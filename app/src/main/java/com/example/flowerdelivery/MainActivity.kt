package com.example.flowerdelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.flowerdelivery.data.AppDatabase
import com.example.flowerdelivery.data.Flower
import com.example.flowerdelivery.data.FlowerRepository
import com.example.flowerdelivery.ui.NavHost
import com.example.flowerdelivery.ui.theme.FlowerDeliveryTheme
import com.example.flowersdel.viewmodel.FlowerViewModel
import com.example.flowersdel.viewmodel.FlowerViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var flowerViewModel: FlowerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val flowerDao = AppDatabase.getDatabase(application).flowerDao()
        val repository = FlowerRepository(flowerDao)
        val factory = FlowerViewModelFactory(repository)
        flowerViewModel = ViewModelProvider(this, factory).get(FlowerViewModel::class.java)

        flowerViewModel.addFlower(Flower(name = "Tulip", description = "A beautiful tulip", price = 20.0, imageUrl = ""))
        flowerViewModel.addFlower(Flower(name = "Rose", description = "A lovely rose", price = 25.0, imageUrl = ""))
        flowerViewModel.addFlower(Flower(name = "Lily", description = "Elegant and fragrant lily", price = 15.0, imageUrl = ""))
        flowerViewModel.addFlower(Flower(name = "Daisy", description = "Bright and cheerful daisy", price = 10.0, imageUrl = ""))
        flowerViewModel.addFlower(Flower(name = "Sunflower", description = "Tall and sunny sunflower", price = 12.0, imageUrl = ""))
        flowerViewModel.addFlower(Flower(name = "Orchid", description = "Exotic and delicate orchid", price = 30.0, imageUrl = ""))
        flowerViewModel.addFlower(Flower(name = "Carnation", description = "Colorful and versatile carnation", price = 18.0, imageUrl = ""))

        enableEdgeToEdge()
        setContent {
            FlowerDeliveryTheme {
                Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                    NavHost()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlowerDeliveryTheme {
        Greeting("Android")
    }
}