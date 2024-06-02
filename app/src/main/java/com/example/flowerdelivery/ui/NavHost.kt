package com.example.flowerdelivery.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flowerdelivery.Greeting
import com.example.flowerdelivery.ui.screens.FeedbackScreen
import com.example.flowerdelivery.ui.screens.FlowerDetailsScreen
import com.example.flowerdelivery.ui.screens.FlowerListScreen
import com.example.flowerdelivery.ui.theme.FlowerDeliveryTheme


@Composable
fun NavHost() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        AppNavigation(navController = navController)
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.FlowerList.route) {
        composable(Screen.FlowerList.route) {
            FlowerListScreen(navController)
        }
        composable(Screen.FlowerDetail.route) { backStackEntry ->
            val flowerId = requireNotNull(backStackEntry.arguments?.getInt("flowerId"))
            FlowerDetailsScreen(navController, flowerId)
        }
        composable(Screen.Feedback.route) {
            FeedbackScreen(navController)
        }
    }
}



sealed class Screen(val route: String) {
    object FlowerList : Screen("flower_list")
    object FlowerDetail : Screen("flower_detail/{flowerId}") // Pass flowerId as argument
    object Feedback : Screen("feedback")
}

@Preview
@Composable
fun NavHostPreview() {
    FlowerDeliveryTheme {
        NavHost()
    }
}