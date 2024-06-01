package com.example.flowersdel.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flowersdel.viewmodel.FlowerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowerDetailScreen(flowerId: Int, navController: NavController) {
    val viewModel: FlowerViewModel = viewModel()
    val flower = viewModel.getFlowerById(flowerId).observeAsState().value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Flower Details") })
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(PaddingValues(all = 16.dp))) {
            if (flower != null) {
                Text("Name: ${flower.name}", style = MaterialTheme.typography.headlineMedium)
                Text("Description: ${flower.description}", style = MaterialTheme.typography.bodyLarge)
                Text("Price: $${flower.price}", style = MaterialTheme.typography.bodyLarge)
            } else {
                Text("Loading...", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}
