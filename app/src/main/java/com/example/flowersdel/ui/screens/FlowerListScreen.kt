package com.example.flowersdel.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flowersdel.viewmodel.FlowerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowerListScreen(navController: NavController, viewModel: FlowerViewModel = viewModel()) {
    val flowers = viewModel.allFlowers.collectAsState(initial = emptyList()).value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Flower List") },
                colors = TopAppBarDefaults.smallTopAppBarColors()
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addFlower") }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Flower")
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(items = flowers, key = { flower -> flower.id }) { flower ->
                ListItem(
                    modifier = Modifier.clickable { navController.navigate("flowerDetail/${flower.id}") },
                    headlineContent = { Text(flower.name) },
                    supportingContent = { Text(flower.description) },
                    overlineContent = { Text("ID: ${flower.id}") }
                )
            }
        }
    }
}
