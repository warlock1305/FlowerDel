package com.example.flowersdel.ui.screens

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.example.flowersdel.viewmodel.FlowerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowerDetailScreen(flowerId: Int, navController: NavController) {
    val viewModel: FlowerViewModel = viewModel()
    val flower = viewModel.getFlowerById(flowerId).observeAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Flower Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(PaddingValues(all = 16.dp))) {
            if (flower != null) {
                val context = LocalContext.current
                AndroidView(
                    factory = { context ->
                        ImageView(context).apply {
                            Glide.with(context)
                                .load(flower.imageUrl)
                                .into(this)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp)
                )

                Text("Name: ${flower.name}", style = MaterialTheme.typography.headlineMedium)
                Text("Description: ${flower.description}", style = MaterialTheme.typography.bodyLarge)

                // Row for Price and Buy button
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Price: $${flower.price}", style = MaterialTheme.typography.bodyLarge)
                    Button(onClick = { /* Handle buy action */ }) {
                        Text("Buy")
                    }
                }

            } else {
                Text("Loading...", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}

