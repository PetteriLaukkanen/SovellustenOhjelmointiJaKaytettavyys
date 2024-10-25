package com.example.restaurantlist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restaurantlist.ui.theme.RestaurantListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestaurantListTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "restaurantList"
                ) {
                    composable("restaurantList") {
                        RestaurantListScreen(navController)
                    }
                    composable("restaurantDetail/{restaurantName}/{restaurantAddress}/{restaurantRating}/{restaurantCuisine}") { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("restaurantName")
                        val address = backStackEntry.arguments?.getString("restaurantAddress")
                        val rating = backStackEntry.arguments?.getString("restaurantRating")?.toDoubleOrNull()
                        val cuisine = backStackEntry.arguments?.getString("restaurantCuisine")
                        if (name != null && address != null && rating != null && cuisine != null) {
                            RestaurantDetailScreen(name, address, rating, cuisine, navController)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RestaurantListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    val filteredRestaurants = remember(searchText) {
        restaurantList.filter { restaurant ->
            restaurant.name.contains(searchText, ignoreCase = true)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Find A Restaurant",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            // Search Bar
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Search Restaurants") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            RestaurantList(filteredRestaurants, navController)
        }
    }
}

@Composable
fun RestaurantList(restaurants: List<Restaurant>, navController: NavController) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(restaurants) { restaurant ->
            RestaurantListItem(restaurant) {
                navController.navigate(
                    "restaurantDetail/${restaurant.name}/${restaurant.address}/${restaurant.rating}/${restaurant.cuisine}"
                )
            }
        }
    }
}

@Composable
fun RestaurantListItem(restaurant: Restaurant, onRestaurantClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRestaurantClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = restaurant.name, fontWeight = FontWeight.Bold)
            Text(text = restaurant.address, color = Color.Gray)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RestaurantDetailScreen(name: String, address: String, rating: Double, cuisine: String, navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = name, color = Color.White, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Address: $address", color = Color.Gray, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Rating: $rating", color = Color.Gray, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Cuisine: $cuisine", color = Color.Gray, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 8.dp))
        }
    }
}

// Data class for Restaurant
data class Restaurant(
    val name: String,
    val address: String,
    val rating: Double,
    val cuisine: String
)

val restaurantList = listOf(
    Restaurant("The Gourmet Kitchen", "123 Food St.", 4.5, "Italian"),
    Restaurant("Sushi World", "456 Sushi Ave.", 4.8, "Japanese"),
    Restaurant("Taco Paradise", "789 Taco Blvd.", 4.2, "Mexican"),
    Restaurant("Burger Heaven", "321 Burger Ln.", 4.0, "American"),
    Restaurant("Pasta House", "147 Noodle St.", 4.3, "Italian"),
    Restaurant("Spicy Curry Palace", "852 Spice Rd.", 4.7, "Indian"),
    Restaurant("Le Petit Bistro", "963 French St.", 4.6, "French"),
    Restaurant("Wok 'n' Roll", "258 Noodle Ave.", 4.1, "Chinese"),
    Restaurant("Pizza Planet", "159 Slice Blvd.", 3.9, "Italian"),
    Restaurant("The BBQ Shack", "753 Grill Ln.", 4.4, "American"),
    Restaurant("Ramen Kingdom", "357 Ramen St.", 4.9, "Japanese"),
    Restaurant("Café Mocha", "123 Coffee Rd.", 4.0, "Cafe"),
    Restaurant("Viva la Vegan", "456 Green St.", 4.6, "Vegan"),
    Restaurant("El Toro Loco", "789 Fiesta Ave.", 4.3, "Mexican"),
    Restaurant("Dim Sum Delight", "159 Dumpling Ln.", 4.5, "Chinese"),
    Restaurant("The Greek Taverna", "258 Olive St.", 4.7, "Greek"),
    Restaurant("Kebab Palace", "963 Spice St.", 4.3, "Middle Eastern"),
    Restaurant("The Hot Pot Spot", "654 Boil Ave.", 4.2, "Chinese"),
    Restaurant("Falafel Corner", "321 Vegan Blvd.", 4.0, "Middle Eastern"),
    Restaurant("Seaside Sushi", "753 Ocean Ave.", 4.8, "Japanese"),
    Restaurant("The Taco Stand", "987 Fiesta St.", 3.8, "Mexican"),
    Restaurant("Steakhouse Supreme", "654 Meat Ln.", 4.9, "American"),
    Restaurant("Pho Haven", "258 Soup Ave.", 4.4, "Vietnamese"),
    Restaurant("The Sushi Spot", "951 Fish Blvd.", 4.2, "Japanese"),
    Restaurant("The Vegan Joint", "753 Plant Ave.", 4.7, "Vegan")
)
