package com.example.flowerdelivery.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.flowerdelivery.R
import com.example.flowerdelivery.data.Flower
import com.example.flowersdel.viewmodel.FlowerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowerListScreen(navController: NavController, viewModel: FlowerViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "FlowerList") },

                actions = {
                    Text(
                        text = "Feedback",
                        modifier = Modifier.clickable {
                            navController.navigate("feedback")
                        }
                    )
                }
            )
        }
    ){
    val flowerList by viewModel.allFlowers.observeAsState(initial = emptyList())

    LazyColumn {
        items(flowerList) { flower ->
            FlowerItem(flower = flower, onClick = { /* Handle item click */ })
        }
    }

}

}
@Composable
fun FlowerItem(flower: Flower, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        val image = loadImageFromUrl(flower.imageUrl)
        if (image != null) {
            Image(bitmap = image, contentDescription = null, modifier = Modifier.size(64.dp))
        }
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = flower.name)
            Text(text = flower.description)
            Text(text = "$${flower.price}")
        }
    }
}

@Composable
fun loadImageFromUrl(url: String): ImageBitmap? {
    // Here you should implement the logic to load the image from the provided URL
    // For demonstration purposes, I'll just return the placeholder image
    return ImageBitmap.imageResource(id = R.drawable.image)
}
@Preview
@Composable
fun FlowerListScreenPreview() {
    val navController = rememberNavController()
    FlowerListScreen(navController = navController)
}