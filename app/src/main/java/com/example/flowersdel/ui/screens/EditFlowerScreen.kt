package com.example.flowersdel.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flowersdel.data.Flower
import com.example.flowersdel.viewmodel.FlowerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFlowerScreen(flowerId: Int, navController: NavController) {
    val viewModel: FlowerViewModel = viewModel()
    val flower by viewModel.getFlowerById(flowerId).observeAsState()


    var name by remember { mutableStateOf(flower?.name ?: "") }
    var description by remember { mutableStateOf(flower?.description ?: "") }
    var price by remember { mutableStateOf(flower?.price?.toString() ?: "") }
    var imageUrl by remember { mutableStateOf(flower?.imageUrl ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Flower") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") }
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") }
            )
            TextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
            )
            TextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("Image URL") }
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    if (name.isNotBlank() && description.isNotBlank() && price.isNotBlank() && imageUrl.isNotBlank()) {
                        viewModel.updateFlower(Flower(id = flowerId, name = name, description = description, price = price.toDouble(), imageUrl = imageUrl))
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotBlank() && description.isNotBlank() && price.isNotBlank() && imageUrl.isNotBlank()
            ) {
                Text("Save Changes")
            }
        }
    }
}
