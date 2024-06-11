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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flowerdelivery.data.AppDatabase
import com.example.flowerdelivery.data.Flower
import com.example.flowerdelivery.data.FlowerRepository
import com.example.flowerdelivery.ui.NavHost
import com.example.flowerdelivery.ui.theme.FlowerDeliveryTheme
import com.example.flowerdelivery.viewmodels.FeedbackViewModel
import com.example.flowerdelivery.viewmodels.OrderViewModel
import com.example.flowersdel.viewmodel.FlowerViewModel
import com.example.flowersdel.viewmodel.FlowerViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

