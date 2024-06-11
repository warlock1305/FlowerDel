package com.example.flowerdelivery.ui.screens

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flowerdelivery.FlowerDeliveryApplication
import com.example.flowerdelivery.data.Flower
import com.example.flowerdelivery.viewmodels.OrderViewModel
import com.example.flowerdelivery.viewmodels.FlowerViewModel
import com.example.flowerdelivery.viewmodels.FlowerViewModelFactory
import com.bumptech.glide.Glide
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowerListScreen(navController: NavController) {
    val application = LocalContext.current.applicationContext as FlowerDeliveryApplication
    val flowerViewModel: FlowerViewModel = viewModel(factory = FlowerViewModelFactory.factory(application))
    val flowers = flowerViewModel.allFlowers.collectAsState(initial = emptyList()).value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Flower List") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("feedback") }) {
                        Icon(Icons.Filled.MailOutline, contentDescription = "Feedback")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            )
        },
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(innerPadding),
        //    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
        ) {
            items(flowers) { flower ->
                FlowerCard(
                    flower = flower,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { navController.navigate("flower_detail/${flower.id}") }
                )
            }
        }
    }
}


@Composable
fun FlowerCard(flower: Flower, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        modifier = modifier.padding(8.dp)
    ) {
        Column {
            AndroidView(
                factory = { context ->
                    ImageView(context).apply {
                        scaleType = ImageView.ScaleType.FIT_XY
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        Glide.with(context)
                            .load(flower.imageUrl)
                            .into(this)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f) // Set the aspect ratio to 1:1 for square images
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                val context = LocalContext.current
                Text(text = flower.name, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Price: $${flower.price}", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
            }
        }
    }
}


