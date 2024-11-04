package com.example.ssovellus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ssovellus.ui.theme.SääSovellusTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val apiKey = "b8a899ed7c600f272a6acf30ece25feb"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SääSovellusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun WeatherScreen(modifier: Modifier = Modifier) {
        var weatherData by remember { mutableStateOf<WeatherData?>(null) }
        var isLoading by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            isLoading = true
            try {
                val response = WeatherService.weatherApi.getWeather("Helsinki", apiKey)
                weatherData = WeatherData(
                    description = response.weather.first().description,
                    temperature = response.main.temp,
                    windSpeed = response.wind.speed
                )
            } catch (e: Exception) {
                // Handle error
            } finally {
                isLoading = false
            }
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(40.dp))
        } else {
            weatherData?.let { weather ->
                Column(modifier = modifier.padding(16.dp)) {
                    Text(text = "Sään kuvaus: ${weather.description}", fontWeight = FontWeight.Bold)
                    Text(text = "Lämpötila: ${weather.temperature} °C", fontWeight = FontWeight.Bold)
                    Text(text = "Tuulen nopeus: ${weather.windSpeed} m/s", fontWeight = FontWeight.Bold)
                }
            } ?: run {
                Text(text = "Säädataa ei voitu ladata.", modifier = modifier)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun WeatherScreenPreview() {
        SääSovellusTheme {
            WeatherScreen()
        }
    }
}
