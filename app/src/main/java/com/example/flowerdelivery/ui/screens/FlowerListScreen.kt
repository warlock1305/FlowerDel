package com.example.flowerdelivery.ui.screens

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flowerdelivery.FlowerDeliveryApplication
import com.example.flowerdelivery.data.Flower
import com.example.flowerdelivery.viewmodels.FlowerViewModel
import com.example.flowerdelivery.viewmodels.FlowerViewModelFactory
import com.bumptech.glide.Glide
import androidx.compose.ui.Alignment.Vertical
import androidx.compose.ui.Alignment.Horizontal
import androidx.compose.ui.graphics.Color
import java.time.Instant
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun FlowerListScreen(navController: NavController) {
    val application = LocalContext.current.applicationContext as FlowerDeliveryApplication
    val flowerViewModel: FlowerViewModel = viewModel(factory = FlowerViewModelFactory.factory(application))
    val flowers = flowerViewModel.allFlowers.collectAsState(initial = emptyList()).value
    var cartVisible by remember { mutableStateOf(false) }
    var cartItems by remember { mutableStateOf(mutableMapOf<Int, Int>()) }


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
                    IconButton(onClick = { cartVisible = true }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Shopping Cart")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            )
        },
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(flowers) { flower ->
                    FlowerCard(
                        flower = flower,
                        onAddToCart = {
                            cartItems[flower.id] = (cartItems[flower.id] ?: 0) + 1
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { navController.navigate("flower_detail/${flower.id}") }
                    )
                }
            }

            AnimatedVisibility(
                visible = cartVisible,
                enter = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(durationMillis = 300)),
                exit = slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(durationMillis = 300))
            ) {
                Box(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    CartScreen(
                        cartItems = cartItems,
                        flowers = flowers,
                        onClose = { cartVisible = false },
                        onRemoveItem = { flowerId ->
                            cartItems.remove(flowerId)
                            cartItems = cartItems.toMutableMap()
                        },
                        onIncreaseQuantity = { flowerId ->
                            cartItems[flowerId] = cartItems[flowerId]!! + 1
                            cartItems = cartItems.toMutableMap()
                        }
                        ,
                        onDecreaseQuantity = { flowerId ->
                            val currentQuantity = cartItems[flowerId] ?: 0
                            if (currentQuantity > 1) {
                                cartItems[flowerId] = currentQuantity - 1
                            } else {
                                cartItems.remove(flowerId)
                            }
                            cartItems = cartItems.toMutableMap()
                        }
                    )
                }
            }
        }
    }
}




@Composable
fun FlowerCard(flower: Flower, onAddToCart: () -> Unit, modifier: Modifier = Modifier) {
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
                    .aspectRatio(1.5f)
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = flower.name, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Price: $${flower.price}", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onAddToCart,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Add to Cart")
                }
            }
        }
    }
}


fun handleBuyAction(address: String, dateTime: Instant) {
    // Handle buy action here
    // You can use the address and dateTime as needed
}

@Composable
fun CartScreen(
    cartItems: Map<Int, Int>,
    flowers: List<Flower>,
    onClose: () -> Unit,
    onRemoveItem: (Int) -> Unit,
    onIncreaseQuantity: (Int) -> Unit,
    onDecreaseQuantity: (Int) -> Unit
) {
    val cartFlowers = flowers.filter { it.id in cartItems.keys }
    val currentDateTime = remember { Clock.System.now() }
    var address by remember { mutableStateOf("") }
    var isDialogOpen by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.8f)
            .padding(16.dp),
        shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 4.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = onClose) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Close Cart")
                    }
                }
                Text(
                    text = "Shopping Cart",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(cartFlowers, key = { it.id }){ flower ->
                        val quantity = cartItems[flower.id] ?: 0
                        Column(modifier = Modifier.padding(bottom = 16.dp)) {
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
                                    .aspectRatio(2f)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = flower.name, style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Price: $${flower.price}", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(onClick = { onDecreaseQuantity(flower.id) }) {
                                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Decrease Quantity")
                                }
                                Text(text = quantity.toString(), style = MaterialTheme.typography.bodyLarge)
                                IconButton(onClick = { onIncreaseQuantity(flower.id) }) {
                                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Increase Quantity")
                                }
                                Spacer(modifier = Modifier.width(130.dp))
                                IconButton(onClick = { onRemoveItem(flower.id) }) {
                                    Icon(Icons.Default.Close, contentDescription = "Remove Item")
                                }
                            }
                        }
                    }
                }
            }

            Button(
                onClick = { isDialogOpen = true },
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
            ) {
                Text(text = "Buy")
            }

            if (isDialogOpen) {
                AlertDialog(
                    onDismissRequest = { isDialogOpen = false },
                    title = { Text("Enter Your Address") },
                    text = {
                        Column {
                            TextField(
                                value = address,
                                onValueChange = { address = it },
                                label = { Text("Address") }
                            )
                            Text(
                                text = "Current Time: ${currentDateTime.toLocalDateTime(TimeZone.currentSystemDefault())}",
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                // Handle buy action here
                                // You can use the address and currentDateTime as needed
                                isDialogOpen = false
                            }
                        ) {
                            Text("Buy")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { isDialogOpen = false }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}
