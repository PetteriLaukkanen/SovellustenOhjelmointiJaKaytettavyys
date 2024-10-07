package com.example.navigointijetpackcomposessa

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Home Screen",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("details") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Go to Details")
            }
        }
    }
}

@Composable
fun DetailsScreen(navController: NavHostController) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Details Screen",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("settings") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Go to Settings")
            }
        }
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Settings Screen",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("home") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Go to Home")
            }
        }
    }
}
