package com.example.flowersdel.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flowersdel.ui.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "flowerList") {
        // Flower routes
        composable("flowerList") {
            FlowerListScreen(navController)
        }
        composable("flowerDetail/{flowerId}") { backStackEntry ->
            FlowerDetailScreen(flowerId = backStackEntry.arguments?.getString("flowerId")?.toInt() ?: -1, navController)
        }
        composable("addFlower") {
            AddFlowerScreen(navController)
        }
        composable("editFlower/{flowerId}") { backStackEntry ->
            EditFlowerScreen(flowerId = backStackEntry.arguments?.getString("flowerId")?.toInt() ?: -1, navController)
        }

        // Order routes
       /* composable("orderList") {
            OrderListScreen(navController)
        }
        composable("orderDetail/{orderId}") { backStackEntry ->
            OrderDetailScreen(orderId = backStackEntry.arguments?.getString("orderId")?.toInt() ?: -1, navController)
        }
        composable("addOrder") {
            AddOrderScreen(navController)
        }
        composable("editOrder/{orderId}") { backStackEntry ->
            EditOrderScreen(orderId = backStackEntry.arguments?.getString("orderId")?.toInt() ?: -1, navController)
        }
*/
        // User routes
       /* composable("userList") {
            UserListScreen(navController)
        }
        composable("userDetail/{userId}") { backStackEntry ->
            UserDetailScreen(userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: -1, navController)
        }
        composable("addUser") {
            AddUserScreen(navController)
        }
        composable("editUser/{userId}") { backStackEntry ->
            EditUserScreen(userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: -1, navController)
        }*/
    }
}

