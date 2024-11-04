package com.example.ssovellus

data class WeatherResponse(
    val weather: List<WeatherDescription>,
    val main: Main,
    val wind: Wind
)

data class WeatherDescription(
    val description: String
)

data class Main(
    val temp: Double
)

data class Wind(
    val speed: Double
)
