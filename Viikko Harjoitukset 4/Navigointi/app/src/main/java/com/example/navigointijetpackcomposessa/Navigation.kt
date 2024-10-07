package com.example.navigointijetpackcomposessa

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun FrontPage() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(navController) }
        composable("details") { DetailsScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}