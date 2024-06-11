package com.example.flowerdelivery.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.flowerdelivery.FlowerDeliveryApplication
import com.example.flowerdelivery.viewmodels.FeedbackViewModel
import com.example.flowerdelivery.viewmodels.FlowerViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(navController: NavController) {
    val application = LocalContext.current.applicationContext as FlowerDeliveryApplication
    val feedbackViewModel: FeedbackViewModel = viewModel(factory = FlowerViewModelFactory.factory(application))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Feedback") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            var rating by remember { mutableStateOf(5f) }
            var feedback by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .widthIn(max = 300.dp)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Rate our services:",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Slider(
                    value = rating,
                    onValueChange = { rating = it },
                    valueRange = 0f..10f,
                    steps = 10,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Text(
                    text = "Rating: ${rating.toInt()}",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Feedback:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start)
                )
                TextField(
                    value = feedback,
                    onValueChange = { feedback = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp),
                    label = { Text(text = "Enter your feedback") },
                    shape = RoundedCornerShape(16.dp),
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Email:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start)
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Your email") },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Button(
                    onClick = { /* Handle submit button click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = null
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@Preview
@Composable
fun FeedbackScreenPreview() {
    val navController = rememberNavController()
    FeedbackScreen(navController = navController)
}
