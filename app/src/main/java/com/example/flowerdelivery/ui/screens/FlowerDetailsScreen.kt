package com.example.flowerdelivery.ui.screens

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flowerdelivery.R
import com.example.flowersdel.viewmodel.FlowerViewModel
import com.bumptech.glide.Glide

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowerDetailsScreen(navController: NavController, flowerId: Int) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Flower Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        val viewModel: FlowerViewModel = viewModel()
        val flower = viewModel.getFlowerById(flowerId).observeAsState().value


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