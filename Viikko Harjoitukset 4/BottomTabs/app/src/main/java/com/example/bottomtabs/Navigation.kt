package com.example.bottomtabs

import androidx.compose.material.icons.Icons
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.mutableStateOf

@Composable
fun FrontPage() {
    val navController = rememberNavController()
    var currentScreen by remember { mutableStateOf("home") }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
    }

    NavigationBar() {
        NavigationBarItem(
            label = { Text("Home") },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            selected = currentScreen == "home",
            onClick = {
                currentScreen = "home"
                navController.navigate("home") {
                    popUpTo("home") { saveState = true }
                }
            },
            // Customize selected and unselected icons and colors as needed
        )
        NavigationBarItem(
            label = { Text("Profile") },
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            selected = currentScreen == "profile",
            onClick = {
                currentScreen = "profile"
                navController.navigate("profile") {
                    popUpTo("home") { saveState = true }
                }
            },
            // Customize selected and unselected icons and colors as needed
        )
    }
}
