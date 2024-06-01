package com.example.flowersdel.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
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
fun AddFlowerScreen(navController: NavController) {
    val viewModel: FlowerViewModel = viewModel()
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    val imageUrl by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Flower") },
                colors = topAppBarColors(
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name¸/") }
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") }
            )
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
            )
            Button(
                onClick = {
                    viewModel.addFlower(Flower(name = name, description = description, price = price.toDouble(), imageUrl = imageUrl))
                    navController.popBackStack()
                },
                enabled = name.isNotBlank() && description.isNotBlank() && price.isNotBlank()
            ) {
                Text("Add Flower")
            }
        }
    }
}
